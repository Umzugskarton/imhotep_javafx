package game.GameProcedures;


import GameEvents.fillUpStorageEvent;
import GameMoves.Move;
import GameMoves.fillUpStorageMove;
import SRVevents.Event;
import game.Game;

public class FillUpStorage implements Procedure {
  private fillUpStorageMove move;
  private Game game;
  private int playerId;

  FillUpStorage(Game game, int playerId) {
    this.game = game;
    this.playerId = playerId;
  }

  public void put(Move move) {
    this.move = (fillUpStorageMove) move;
  }

  public fillUpStorageEvent exec() {
    this.game.addStonesToStorage(playerId);

    return new fillUpStorageEvent(playerId, game.getStorage(playerId));
  }
}
