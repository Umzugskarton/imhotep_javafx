package game;

import GameEvents.GameInfoEvent;
import GameEvents.TurnEvent;
import GameMoves.Move;
import GameMoves.ActionCardMove;
import SRVevents.Event;
import game.GameProcedures.Procedure;
import game.GameProcedures.ProcedureFactory;
import game.board.*;
import lobby.Lobby;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import socket.ClientListener;
import user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Game implements Runnable {
    private final Logger log = LoggerFactory.getLogger(getClass().getName());



    private int gameID;
    private Lobby lobby;
    private Ship[] ships;
    private ArrayList<Integer> storages;
    private Player[] order;
    private int currentPlayer;
    private int round;


    //StoneSites
    private Pyramids pyramids;
    private Market market;
    private Obelisks obelisks;
    private Temple temple;
    private BurialChamber burialChamber;

    private ClientListener clientListener;
    private Move nextMove = null;
    private List<Event> executedMoves;

    public Game(Lobby lobby, ClientListener clientListener) {
        this.lobby = lobby;
        this.gameID = this.lobby.getLobbyID();
        this.clientListener = clientListener;
        this.executedMoves = new ArrayList<>();
        lobby.show(false);

        this.ships = new Ship[lobby.getSize()];
        this.order = new Player[lobby.getSize()];
        this.storages = new ArrayList<>();
        setGame();

        this.pyramids = new Pyramids(lobby.getSize(), 1);
        this.obelisks = new Obelisks(lobby.getSize());
        this.market = new Market(lobby.getSize());
        this.temple = new Temple(lobby.getSize());
        this.burialChamber = new BurialChamber(lobby.getSize());
        setStartCards();
    }

    public void resetCurrentShips() {
        for (int i = 0; i <= lobby.getSize() - 1; i++) {
            this.ships[i] = new Ship(ThreadLocalRandom.current().nextInt(1, 4));
        }
    }


    private void setGame() {
        int seq = ThreadLocalRandom.current().nextInt(0, this.lobby.getSize() - 1);
        for (int i = 0; i <= lobby.getSize() - 1; i++) {
            this.ships[i] = new Ship(ThreadLocalRandom.current().nextInt(1, 4));
            this.order[i] = new Player(lobby.getUsers()[seq], i);
            this.storages.add(i, i+1);
            seq = (seq + 1) % lobby.getSize();
        }
    }

    private void sendAll(Event event) {
        for (Player player : this.order) {
            sendTo(player.getUser(), event);
        }
    }

    private void sendAll(GameInfoEvent event) {
        for (Player player : this.order) {
            event.setMyId(player.getId());
            sendTo(player.getUser(), event);
        }
    }

    private void sendTo(User user, Event event) {
        this.clientListener.getServer().sendTo(event, user.getUsername());
    }

    private GameInfoEvent getGameInfo() {
        GameInfoEvent gameInfo = new GameInfoEvent();

        String[] users = new String[this.lobby.getSize()];
        for (int i = 0; i <= this.lobby.getSize() - 1; i++) {
            users[i] = this.order[i].getUser().getUsername();
        }

        for (Ship ship : ships) {
            gameInfo.setCurrentShips(getCargoAsIntArrayByShip(ship));
        }
        gameInfo.setOrder(users);

        gameInfo.setTurnTime(lobby.getExecutor().getTurnTime());
        gameInfo.setRound(this.round);
        gameInfo.setStorages(this.storages);

        return gameInfo;
    }

    public int[] getCargoAsIntArrayByShip(Ship ship){
        int[] shipInt = new int[ship.getStones().length];
        for (int i = 0; i < ship.getStones().length; i++) {
            if (ship.getStones()[i] != null) {
                shipInt[i] = ship.getStones()[i].getPlayer().getId();
            } else {
                shipInt[i] = -1;
            }
        }
        return shipInt;
    }

    private void setStartCards() {

    }

    @Override
    public void run() {
        for (int i = 1; i <= 6; i++) {
            this.round = i;
            sendAll(getGameInfo());
            while (!allshipsDocked())
                for (int player = 0; player <= this.order.length - 1; player++) {
                    currentPlayer = player;
                    setActivePlayer(player);
                    waitForMove(player);

                    if (this.nextMove != null) {
                        if (nextMove.getType().equals("actionCard")) {
                            ActionCardMove ac = (ActionCardMove) nextMove;
                            for (Move move : ac.getMoves()) {
                                executeMove(move);
                            }
                        } else {
                            int tryed = 0;
                            while (!executeMove() && tryed < 2) {
                                waitForMove(player);
                                tryed++;
                            }
                        }
                    }
                    else
                        log.error("Kein Spielzug gesetzt!");
                    nextMove = null;
                    //Informiert alle User über den/die ausgeführten Move/s
                    for (Event e : executedMoves) {
                        sendAll(e);
                    }
                    executedMoves.clear();

                    if (allshipsDocked()) {
                        break;
                    }
                }
            resetCurrentShips();
        }
    }

    public BurialChamber getBurialChamber() {
        return burialChamber;
    }

    public Market getMarket() {
        return market;
    }

    public Obelisks getObelisks() {
        return obelisks;
    }

    public Pyramids getPyramids() {
        return pyramids;
    }

    public Temple getTemple() {
        return temple;
    }

    public Ship[] getShips() {
        return ships;
    }

    private void setActivePlayer(int player) {
        for (Player p : this.order) {
            sendTo(p.getUser(), new TurnEvent(p == this.order[player], this.order[player].getUser().getUsername()));
        }
    }

    private boolean executeMove() {
        ProcedureFactory pf = new ProcedureFactory(currentPlayer, this);
        Procedure nextProcedure = pf.getProcedure(nextMove.getType(), nextMove);
        executedMoves.add(nextProcedure.exec());
        return true;
    }

    private boolean executeMove(Move move) {
        ProcedureFactory pf = new ProcedureFactory(currentPlayer, this);
        Procedure nextProcedure = pf.getProcedure(move.getType(), move);
        executedMoves.add(nextProcedure.exec());
        return true;
    }

    private boolean allshipsDocked() {
        int haven = 0;
        for (Ship ship : this.ships) {
            haven++;
        }

        return haven == 0;
    }

    private  void waitForMove(int p) {
        log.info("[Lobby " + this.lobby.getLobbyID() + "] Warte auf Spielzug von Spieler " + (p + 1) + " (Name: " + this.order[p].getUser().getUsername() + ")");
        lobby.getExecutor().waitForMove();
        nextMove = lobby.getExecutor().getMove();
    }


    public void addStonesToStorage(int playerId) {
        if (storages.get(playerId)+3 >5)
            storages.set(playerId, 5);
        else
            storages.set(playerId, storages.get(playerId)+3 );
    }

    public int getStorage(int playerID) {
        return storages.get(playerID);
    }

    public void decrPlayerStorage(int playerID){
        int storage = storages.get(playerID)-1;
        storages.set(playerID, storage);
    }

    public int getGameID() {
        return gameID;
    }

    public void updateRound(int round) {
        this.round = round;
    }

    public void updateCboats(Ship[] ships) {
        this.ships = ships;
    }

    public Ship getShipByID(int shipId) {
        return ships[shipId];
    }

    public Player[] getOrder() {
        return order;
    }
}
