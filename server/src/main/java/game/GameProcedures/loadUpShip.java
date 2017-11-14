package game.GameProcedures;


import GameEvents.fillUpStorageEvent;
import GameMoves.Move;
import GameMoves.loadUpShipMove;
import SRVevents.Event;
import game.Game;

public class loadUpShip implements Procedure {
  private loadUpShipMove move;
  private Game game;
  private int playerId;

  loadUpShip(Game game, int playerId) {
    this.game = game;
    this.playerId = playerId;
  }

  public void put(Move move) {
    this.move = (loadUpShipMove) move;
  }

  public Event exec() {

    move.getShipId();
    move.getPosition();

    this.game.addStonesToStorage(this.playerId);

    return new fillUpStorageEvent();
  }
}
