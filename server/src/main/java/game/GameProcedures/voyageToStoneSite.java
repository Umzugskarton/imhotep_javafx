package game.GameProcedures;

import GameEvents.fillUpStorageEvent;
import GameMoves.Move;
import GameMoves.voyageToStoneSiteMove;
import SRVevents.Event;
import game.Game;

public class voyageToStoneSite implements Procedure {
  private voyageToStoneSiteMove move;
  private Game game;
  private int playerId;

  voyageToStoneSite(Game game, int playerId) {
    this.game = game;
    this.playerId = playerId;
  }

  public void put(Move move) {
    this.move = (voyageToStoneSiteMove) move;
  }

  public Event exec() {

    this.game.addStonesToStorage(this.playerId);

    return new fillUpStorageEvent();
  }
}
