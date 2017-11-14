package game;

import GameEvents.gameInfoEvent;
import GameEvents.turnEvent;
import GameMoves.Move;
import GameMoves.actionCardMove;
import SRVevents.Event;
import game.GameProcedures.Procedure;
import game.GameProcedures.ProcedureFactory;
import game.board.Pyramids;
import game.board.Ship;
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
  private boolean[] storages;
  private Player[] order;
  private int currentPlayer;
  private int round;
  private Pyramids pyramids;
  private ClientListener clientListener;
  private Move Nextmove = null;
  private List<Event> executedMoves;
  private Event event = null;

  public Game(Lobby lobby, ClientListener clientListener) {
    this.lobby = lobby;
    this.gameID = this.lobby.getLobbyID();
    this.clientListener = clientListener;
    executedMoves = new ArrayList<>();
    lobby.show(false);
    ships = new Ship[lobby.getSize()];
    order = new Player[lobby.getSize()];
    storages = new boolean[lobby.getSize() * 5];
    setGame();
    pyramids = new Pyramids(lobby.getSize(), 1);
    setStartCards();
    sendAll(getGameinfo());
    run();
  }

  public void resetCboats() {
    for (int i = 0; i <= lobby.getSize() - 1; i++) {
      this.ships[i] = new Ship(ThreadLocalRandom.current().nextInt(1, 4));
    }
  }

  public void setGame() {
    int seq = ThreadLocalRandom.current().nextInt(0, this.lobby.getSize() - 1);
    for (int i = 0; i <= lobby.getSize() - 1; i++) {
      this.ships[i] = new Ship(ThreadLocalRandom.current().nextInt(1, 4));
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
    for (Ship ship : ships) {
      int[] shipInt = new int[ship.getStones().length];
      for (int i = 0; i < ship.getStones().length; i++) {
        if (ship.getStones()[i] != null) {
          shipInt[i] = ship.getStones()[i].getPlayer().getId();
        }
      }
      gameInfo.setCboats(shipInt);
    }
    gameInfo.setOrder(users);

    gameInfo.setRound(this.round);
    gameInfo.setStorages(this.storages);

    return gameInfo;
  }

  public void setStartCards() {

  }


  @Override
  public void run() {
    for (int i = 1; i <= 6; i++) {
      this.round = i;
      sendAll(getGameinfo());
      while (!AllshipsDocked())
        for (int player = 0; player <= this.order.length; player++) {
          currentPlayer = player;
          switchPlayer(player);

          waitforMove(player);

            if (this.Nextmove != null) {
              if (Nextmove.getType().equals( "actionCard")){
                actionCardMove ac = (actionCardMove) Nextmove;
               for (Move move: ac.getMoves()){
                 executeMove(move);
               }
              }
              else {
              int tryed = 0;
              while (!executeMove() && tryed < 2) {
                waitforMove(player);
                tryed++;
              }
            }
          }

          if (AllshipsDocked()) {
            break;
          }
          //Informiert alle User über den/die ausgeführten Move/s
          for (Event e: executedMoves) {
            sendAll(e);
          }
        }
    }
  }

  private void switchPlayer(int player) {
    for (Player p : this.order) {
      sendTo(p.getUser(), new turnEvent(p == this.order[player]));
    }
  }

  private boolean executeMove() {
    ProcedureFactory pf = new ProcedureFactory(currentPlayer, this);
    Procedure nextProcedure = pf.getProcedure(Nextmove.getType(), Nextmove);
    executedMoves.add(nextProcedure.exec());
    return true;
  }
  private boolean executeMove(Move move) {
    ProcedureFactory pf = new ProcedureFactory(currentPlayer, this);
    Procedure nextProcedure = pf.getProcedure(move.getType(), move);
    sendAll(nextProcedure.exec());
    return true;
  }

  private boolean AllshipsDocked() {
    int haven = 0;
    for (Ship ship : this.ships) {
      haven++;
    }
    return haven > 0;
  }

  synchronized void waitforMove(int p) {
    log.info("Lobby" + this.lobby.getLobbyID() + ": Warte auf Spielzug von Spieler nr." + p + 1 + " " + this.order[p].getUser().getUsername());
    try {
      this.wait(32000);
    } catch (InterruptedException e) {
      log.error(e.getMessage());
    }
  }

  public synchronized void setNextmove(Move nextmove) {
    this.Nextmove = nextmove;
    this.notify();
  }

  public void addStonesToStorage(int playerId) {
    int set = 0;
    for (int i = (playerId * 5); i < i + 5; i++) {
      if (!storages[i]) {
        storages[i] = true;
        set++;
      }
      if (set == 3) {
        break;
      }
    }
  }

  public boolean[] getStorage(int playerID) {
    boolean[] playerStorage = new boolean[5];
    for (int i = playerID * 5; i < (playerID * 5) + 5; i++) {
      playerStorage[i % 5] = storages[i];
    }
    return playerStorage;
  }

  public boolean[] getStorages() {
    return storages;
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
}
