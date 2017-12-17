package game.GameProcedures;


import GameEvents.FillUpStorageEvent;
import GameMoves.Move;
import GameMoves.fillUpStorageMove;
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

  public FillUpStorageEvent exec() {
    this.game.addStonesToStorage(playerId);

    return new FillUpStorageEvent(playerId, game.getStorage(playerId));
  }
}
