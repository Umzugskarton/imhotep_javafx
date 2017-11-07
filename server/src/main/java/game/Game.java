package game;

import GameEvents.gameInfoEvent;
import GameEvents.turnEvent;
import GameMoves.Move;
import GameObjects.Bauwerke.Pyramid;
import GameObjects.Boat;
import SRVevents.Event;
import lobby.Lobby;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import socket.ClientListener;
import user.User;

import java.util.concurrent.ThreadLocalRandom;

public class Game implements Runnable {
    private final Logger log = LoggerFactory.getLogger(getClass().getName());

    private int gameID;
    private Lobby lobby;
    private Boat[] cboats;
    private boolean[][] storages;
    private Player[] order;
    private int round;
    private Pyramid pyramid;
    private ClientListener clientListener;
    private Move move = null;
    private Event event = null;

    public Game(Lobby lobby, ClientListener clientListener) {
        this.lobby = lobby;
        this.gameID = this.lobby.getLobbyID();
        this.clientListener = clientListener;
        lobby.show(false);
        this.cboats = new Boat[lobby.getSize()];
        order = new Player[lobby.getSize()];
        storages = new boolean[lobby.getSize() - 1][5];
        setGame();
        this.pyramid = new Pyramid();
        setStartCards();
        sendAllGameinfo();
        run();
    }

    public void resetCboats() {
        for (int i = 0; i <= lobby.getSize() - 1; i++) {
            this.cboats[i] = new Boat(ThreadLocalRandom.current().nextInt(1, 4));
        }
    }

    public void setGame() {
        int seq = ThreadLocalRandom.current().nextInt(0, this.lobby.getSize() - 1);
        for (int i = 0; i <= lobby.getSize() - 1; i++) {
            this.cboats[i] = new Boat(ThreadLocalRandom.current().nextInt(1, 4));
            this.order[i] = new Player(lobby.getUsers()[seq], i);
            seq = seq + 1 % lobby.getSize() - 1;
        }
    }

    public void sendAll(Event event) {
        for (Player player : this.order) {
            sendTo(player.getUser(), event);
        }
    }


    public void sendTo(User user, Event event) {
        this.clientListener.getServer().sendTo(event, user.getUsername());
    }

    public gameInfoEvent getGameinfo() {
        String[] users = new String[this.lobby.getSize() - 1];

        for (int i = 0; i <= this.lobby.getSize() - 1; i++) {
            users[i] = this.order[i].getUser().getUsername();
        }

        gameInfoEvent gameInfo = new gameInfoEvent();
        gameInfo.setCboats(this.cboats);
        gameInfo.setOrder(users);
        gameInfo.setRound(this.round);
        gameInfo.setStorages(this.storages);

        return gameInfo;
    }

    public void setStartCards() {

    }


    @Override
    public void run() {
        for (int i = 0; i <= 6; i++) {
            this.round = i;
            sendAll(getGameinfo());
            while (!AllshipsDocked())
                for (int player = 0; player <= this.order.length; player++) {
                    switchPlayer(player);
                    try {
                        waitforMove(player);
                    } catch (InterruptedException e) {
                        log.error(e.getMessage());
                    }
                    if (this.move != null) {
                        executeMove();
                    }

                    if (AllshipsDocked()) {
                        break;
                    }
                    //Todo:update alle Player über Spielzug
                }
        }
    }

    private void switchPlayer(int player) {
        for (Player p : this.order) {
            sendTo(p.getUser(), new turnEvent(p == this.order[player]));
        }
    }

    private boolean executeMove() {

        return true;
    }

    private boolean AllshipsDocked() {
        int haven = 0;
        for (Boat boat : this.cboats) {
            haven++;
        }
        return haven > 0;
    }

    synchronized void waitforMove(int p) throws InterruptedException {
        log.info("Lobby" + this.lobby.getLobbyID() + ": Warte auf Spielzug von Spieler nr." + p + 1 + " " + this.order[p]);
        this.wait(32000);
    }

    public synchronized void setMove(Move move) {
        this.move = move;
        this.notify();
    }

    public int getGameID() {
        return gameID;
    }

    public void updateStorages(boolean[][] storages) {
        this.storages = storages;
    }

    public void updateRound(int round) {
        this.round = round;
    }

    public void updateCboats(Boat[] cboats) {
        this.cboats = cboats;
    }
}
