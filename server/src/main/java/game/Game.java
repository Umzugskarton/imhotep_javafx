package game;

import data.user.User;
import events.Event;
import events.SiteType;
import events.app.game.GameInfoEvent;
import events.app.game.TurnEvent;
import events.app.game.UpdatePointsEvent;
import events.app.game.WinEvent;
import game.board.BurialChamber;
import game.board.Market;
import game.board.Obelisks;
import game.board.Pyramids;
import game.board.Ship;
import game.board.Site;
import game.board.StoneSite;
import game.board.Temple;
import game.board.cards.CardDeck;
import game.gameprocedures.Procedure;
import game.gameprocedures.ProcedureFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import lobby.Lobby;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import requests.gamemoves.CardType;
import requests.gamemoves.Move;
import socket.ClientListener;

public class Game implements Runnable {

  private static final int NUMBER_OF_SHIPS = 4;
  private final Logger log = LoggerFactory.getLogger(getClass().getName());

  private int gameID;
  private Lobby lobby;
  private Ship[] ships = new Ship[NUMBER_OF_SHIPS];
  private Player[] players;
  private int currentPlayer;
  private int round;
  private CardDeck cardDeck = new CardDeck();

  //StoneSites
  private ArrayList<StoneSite> sites = new ArrayList<>();
  private Pyramids pyramids;
  private Market market;
  private Obelisks obelisks;
  private Temple temple;
  private BurialChamber burialChamber;
  // Reihenfolge wichtig , muss mit der dreingabe der Sites in den sites Array übereinstimmen!
  private SiteType[] siteTypesArray = {
      SiteType.MARKET, SiteType.PYRAMID, SiteType.TEMPLE,
      SiteType.BURIALCHAMBER, SiteType.OBELISKS
  };
  private ArrayList<SiteType> siteTypes = new ArrayList<>(Arrays.asList(siteTypesArray));

  private ClientListener clientListener;
  private Move nextMove = null;
  private ProcedureFactory pf;
  private MoveExecutor executor = new MoveExecutor();

  public Game(Lobby lobby, ClientListener clientListener) {
    this.lobby = lobby;
    this.gameID = this.lobby.getLobbyID();
    this.clientListener = clientListener;
    initialize();
  }

  private void initialize() {
    int size = lobby.getSize();
    players = new Player[size];
    market = new Market(size, cardDeck.getDeck());
    pyramids = new Pyramids(size);
    obelisks = new Obelisks(size);
    temple = new Temple(size);
    burialChamber = new BurialChamber(size);
    sites.add(pyramids);
    sites.add(temple);
    sites.add(burialChamber);
    sites.add(obelisks);

    for (int i = 0; i < NUMBER_OF_SHIPS; i++) {
      ships[i] = new Ship(i);
    }
    List<User> lobbyUsers = new ArrayList<>(Arrays.asList(lobby.getUsers()));
    for (int i = 0; i < size; i++) {
      players[i] = new Player(lobbyUsers.get(i), i);
      players[i].addStones(i + 2);
    }
  }

  private void resetCurrentShips() {
    for (int i = 0; i < NUMBER_OF_SHIPS; i++) {
      this.ships[i] = new Ship(i);
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

  private void updatePoints() {
    sendAll(new UpdatePointsEvent(getPointsSum(), gameID));
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
      gameInfo.setCurrentShips(ship.getCargoAsIntArrayByShip());
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
    ArrayList<CardType> cards = new ArrayList<>();
    market.getActiveCards().forEach(card -> cards.add(card.getType()));

    gameInfo.setCards(cards);
    gameInfo.setSiteTypes(siteTypes);
    gameInfo.setSitesAllocation(dockedSites);
    gameInfo.setOrder(users);
    gameInfo.setTurnTime(MoveExecutor.TURN_TIME);
    gameInfo.setRound(this.round);
    ArrayList<Integer> storages = new ArrayList<>();
    for (Player p : players) {
      storages.add(p.getStones());
    }
    gameInfo.setStorages(storages);
    return gameInfo;
  }



  @Override
  public void run() {
    int numberOfRounds = 6;
    for (int i = 1; i <= numberOfRounds; i++) {
      this.round = i;
      sites.forEach(Site::prepareRound);
      market.prepareRound();
      sendAll(getGameInfo());
      while (!allshipsDocked()) {
        for (int player = 0; player < this.players.length; player++) {
          currentPlayer = player; //Leichterer Zugriff auf aktuellen Player
          setActivePlayer(player);
          waitForMove(player);
          if (this.nextMove != null) {
            executeMove();
          } else {
            log.error("[ Game: {} ] Kein Spielzug gesetzt von Spieler {}!", gameID,
                players[currentPlayer].getId());
          }
          nextMove = null;
          if (allshipsDocked()) {
            break;
          }
        }
      }
      resetCurrentShips();
      addPointsEndOfRound();
    }
    addPointsEndOfGame();
    nominateWinner();
  }

  private void addPointsEndOfGame() {
    int[] burialChamberPoints = burialChamber.getPoints();
    int[] obelisksPoints = obelisks.getPoints();
    for (int player = 0; player < this.players.length; player++) {
      this.players[player].addPoints(burialChamberPoints[player]);
      this.players[player].addPoints(obelisksPoints[player]);
    }
    updatePoints();
  }

  private void addPointsEndOfRound() {
    int[] templePoints = temple.getPoints();
    for (int player = 0; player < this.players.length; player++) {
      this.players[player].addPoints(templePoints[player]);
    }
    updatePoints();
  }

  private void nominateWinner() {
    Player winner = null;
    String[][] playerResult = new String[2][players.length];
    int i = 0;
    for (Player p : players) {
      if (winner == null || p.getPoints() > winner.getPoints()) {
        winner = p;
      }
      playerResult[0][i] = p.getUser().getUsername();
      playerResult[1][i] = String.valueOf(p.getPoints());
      i++;
    }
    sendAll(new WinEvent(winner.getUser().getUsername(), playerResult, gameID));
  }

  public Site getSiteByType(SiteType siteType) {
    EnumMap<SiteType, Site> site = new EnumMap<>(SiteType.class);
    site.put(SiteType.PYRAMID, pyramids);
    site.put(SiteType.MARKET, market);
    site.put(SiteType.OBELISKS, obelisks);
    site.put(SiteType.TEMPLE, temple);
    site.put(SiteType.BURIALCHAMBER, burialChamber);
    return site.get(siteType);
  }

  public Ship[] getShips() {
    return ships;
  }

  private void setActivePlayer(int player) {
    pf = new ProcedureFactory(player, this);
    for (Player p : this.players) {
      sendTo(p.getUser(),
          new TurnEvent(p == this.players[player], this.players[player].getUser().getUsername(),
              gameID));
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

  public Player getPlayerByUser(User user){
    for (Player player : players) {
      if (player.getUser().equals(user))
        return player;
    }
    return null;
  }

  private void waitForMove(int p) {
    log.info("[Game:" + gameID + "] Warte auf Spielzug von Spieler " + (p + 1) + " (Name: "
        + this.players[p].getUser().getUsername() + ")");
    executor.waitForMove();
    nextMove = executor.getMove();
  }

  public int getCurrentPlayer() {
    return currentPlayer;
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

  public int getGameID() {
    return gameID;
  }

  public void setCurrentPlayer(int currentPlayer) {
    this.currentPlayer = currentPlayer;
  }

  private int testRound = 0;

  public void runOneRoundTest(Move[] moves) {
    testRound++;
    this.sites.forEach(Site::prepareRound);
    sendAll(getGameInfo());
    for (int player = 0; player < this.players.length; player++) {
      currentPlayer = player; //Leichterer Zugriff auf aktuellen Player
      pf = new ProcedureFactory(player, this);
      nextMove = setTestMove(moves[player]);
      if (this.nextMove != null) {
        executeMove();
      } else {
        log.error("[ Game: " + gameID + " ] Kein Spielzug gesetzt von Spieler "
            + players[currentPlayer] + "! ");
      }
    }
    addPointsEndOfRound();
    addPointsEndOfGame();
    if (testRound == 3) {
      nominateWinner();
      log.info("geschafft");
    }


  }

  public Move setTestMove(Move move) {
    return move;
  }
}
