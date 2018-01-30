package game;

import GameEvents.GameInfoEvent;
import GameEvents.TurnEvent;
import GameEvents.UpdatePointsEvent;
import GameEvents.WinEvent;
import GameMoves.Move;
import SRVevents.Event;
import game.GameProcedures.Procedure;
import game.GameProcedures.ProcedureFactory;
import game.board.BurialChamber;
import game.board.Cards.CardDeck;
import game.board.Market;
import game.board.Obelisks;
import game.board.Pyramids;
import game.board.Ship;
import game.board.StoneSite;
import game.board.Temple;
import lobby.Lobby;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import socket.ClientListener;
import user.User;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Game implements Runnable {

  private final Logger log = LoggerFactory.getLogger(getClass().getName());

  private int gameID;
  private Lobby lobby;
  private Ship[] ships;
  private Player[] players;
  private int numberOfShips;
  private int currentPlayer;
  private int round;
  private CardDeck cardDeck = new CardDeck();

  //StoneSites
  private ArrayList<StoneSite> sites;
  private Pyramids pyramids;
  private Market market;
  private Obelisks obelisks;
  private Temple temple;
  private BurialChamber burialChamber;
  // Reihenfolge wichtig , muss mit der dreingabe der Sites in den sites Array übereinstimmen!
  private String[] siteString = {"Market", "Pyramids", "Temple", "BurialChamber", "Obelisks"};

  private ClientListener clientListener;
  private Move nextMove = null;
  ProcedureFactory pf;
  private MoveExecutor executor;

  public Game(Lobby lobby, ClientListener clientListener) {
    this.lobby = lobby;
    this.gameID = this.lobby.getLobbyID();
    this.clientListener = clientListener;
    lobby.show(false);

    this.numberOfShips = 4;
    this.ships = new Ship[numberOfShips];
    this.players = new Player[lobby.getSize()];
    setGame();
    executor = new MoveExecutor();
    this.market = new Market(lobby.getSize());
    this.pyramids = new Pyramids(lobby.getSize());
    this.obelisks = new Obelisks(lobby.getSize());
    this.temple = new Temple(lobby.getSize());
    this.burialChamber = new BurialChamber(lobby.getSize());
    sites = new ArrayList<>();
    //Reihenfolge beachten!
    sites.add(pyramids);
    sites.add(temple);
    sites.add(burialChamber);
    sites.add(obelisks);

    market.addCards(cardDeck.getDeck());
  }

  public void resetCurrentShips() {
    for (int i = 0; i < lobby.getSize(); i++) {
      this.ships[i] = new Ship(i, ThreadLocalRandom.current().nextInt(1, 4));
    }
  }

  private void setGame() {
    int seq = ThreadLocalRandom.current().nextInt(0, this.lobby.getSize() - 1);
    for (int i = 0; i < numberOfShips; i++) {
      this.ships[i] = new Ship(i, ThreadLocalRandom.current().nextInt(1, 4));
    }
    for (int i = 0; i < lobby.getSize(); i++) {
      this.players[i] = new Player(lobby.getUsers()[seq], i);
      this.players[i].getSupplySled().addStones(i + 2);
      seq = (seq + 1) % lobby.getSize();
    }
  }

  public void sendAll(Event event) {
    for (Player player : this.players) {
      sendTo(player.getUser(), event);
    }
  }

  public void sendAll(GameInfoEvent event) {
    for (Player player : this.players) {
      event.setMyId(player.getId());
      sendTo(player.getUser(), event);
    }
  }

  public void sendAll(WinEvent event) {
    for (Player p : players) {
      event.setWinning(event.getWinner().equals(p.getUser().getUsername()));
      sendTo(p.getUser(), event);
    }
  }

  public int[] getPointsSum() {
    int[] points = new int[players.length];
    for (Player player : this.players) {
      points[player.getId()] = player.getPoints();
    }
    return points;
  }

  public void updatePoints() {
    sendAll(new UpdatePointsEvent(getPointsSum()));
  }

  public void updatePyramids() {
    int[] newPoints = pyramids.getPointsAndFinishTurn();
    for (int player = 0; player < this.players.length; player++) {
      this.players[player].addPoints(newPoints[player]);
    }
    updatePoints();
  }

  public void sendTo(User user, Event event) {
    this.clientListener.getServer().sendTo(event, user.getUsername());
  }

  private GameInfoEvent getGameInfo() {
    GameInfoEvent gameInfo = new GameInfoEvent();

    String[] users = new String[this.lobby.getSize()];
    for (int i = 0; i < this.lobby.getSize(); i++) {
      users[i] = this.players[i].getUser().getUsername();
    }

    for (Ship ship : ships) {
      gameInfo.setCurrentShips(getCargoAsIntArrayByShip(ship));
    }

    int numberOfSites = 5;
    int[] dockedSites = new int[numberOfSites];

    if (market.isDocked()) {
      dockedSites[0] = market.getDockedShip().getId();
    } else {
      dockedSites[0] = -1;
    }
    int j = 1;
    for (StoneSite site : sites) {
      if (site.isDocked()) {
        dockedSites[j] = site.getDockedShip().getId();
      } else {
        dockedSites[j] = -1;
      }
      j++;
    }

    gameInfo.setSiteString(siteString);
    gameInfo.setSitesAllocation(dockedSites);
    gameInfo.setOrder(users);
    gameInfo.setTurnTime(executor.getTurnTime());
    gameInfo.setRound(this.round);
    // TODO change gameInfo to make this easier
    ArrayList<Integer> storages = new ArrayList<>();
    for (Player p : players) {
      storages.add(p.getSupplySled().getStones());
    }
    gameInfo.setStorages(storages);

    return gameInfo;
  }

  public int[] getCargoAsIntArrayByShip(Ship ship) {
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

  @Override
  public void run() {
    for (int i = 1; i <= 6; i++) {
      this.round = i;
      sendAll(getGameInfo());
      while (!allshipsDocked()) {
        for (int player = 0; player < this.players.length; player++) {
          currentPlayer = player; //Leichterer Zugriff auf aktuellen Player
          setActivePlayer(player);
          waitForMove(player);
          if (this.nextMove != null) {
            executeMove();
          } else {
            log.error("[ Game: " + gameID + " ] Kein Spielzug gesetzt von Spieler "
                + players[currentPlayer] + "! ");
          }
          nextMove = null;

          if (allshipsDocked()) {
            break;
          }
        }
        resetCurrentShips();
        //Addiert die Punkte der Spieler aus dem Tempel zu ihren Punktzahlen
        //Ende jeder Runde
        int[] templePoints = temple.getPoints();
        for (int player = 0; player < this.players.length; player++) {
          this.players[player].addPoints(templePoints[player]);
        }
        updatePoints();
      }
      //Addiert die Punkte der Spieler aus der Grabkammer und den Obelisken zu ihren Punktzahlen
      //Ende des Spiels
      int[] burialChamberPoints = burialChamber.getPoints();
      int[] obelisksPoints = obelisks.getPoints();
      for (int player = 0; player < this.players.length; player++) {
        this.players[player].addPoints(burialChamberPoints[player]);
        this.players[player].addPoints(obelisksPoints[player]);
      }
      updatePoints();
    }
    nominateWinner();
  }

  // TODO sichergehen, dass die Player ihre tatsächlichen Punkte am Spielende enthalten
  private void nominateWinner() {
    Player winner = null;
    for (Player p : players) {
      if (winner == null || p.getPoints() > winner.getPoints()) {
        winner = p;
      }
    }
    sendAll(new WinEvent(winner.getUser().getUsername()));
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
    pf = new ProcedureFactory(player, this);
    for (Player p : this.players) {
      sendTo(p.getUser(),
          new TurnEvent(p == this.players[player], this.players[player].getUser().getUsername()));
    }
  }

  private boolean executeMove() {
    Procedure nextProcedure = pf.getProcedure(nextMove);
    executeProcedure(nextProcedure);
    return true;
  }

  private void executeProcedure(Procedure procedure) {
    log.info("[Game:" + gameID + "] führe Spielzug " + procedure.getClass().getName() + " aus für "
        + currentPlayer + " (Spieler: " + this.players[currentPlayer].getUser().getUsername()
        + ")");

    //Informiert alle User über den/die ausgeführten Move/s
    sendAll(procedure.exec());
  }

  public boolean executeMove(Move move) {
    Procedure nextProcedure = pf.getProcedure(move);
    executeProcedure(nextProcedure);
    return true;
  }

  private boolean allshipsDocked() {
    for (Ship ship : this.ships) {
      if (!ship.isDocked()) {
        return false;
      }
    }
    return true;
  }

  private void waitForMove(int p) {
    log.info("[Game:" + gameID + "] Warte auf Spielzug von Spieler " + (p + 1) + " (Name: "
        + this.players[p].getUser().getUsername() + ")");
    executor.waitForMove();
    nextMove = executor.getMove();
  }


  public MoveExecutor getExecutor() {
    return executor;
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

  public Player getPlayer(int id) {
    return players[id];
  }
}
