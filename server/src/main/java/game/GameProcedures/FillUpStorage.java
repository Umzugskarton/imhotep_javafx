package game.GameProcedures;


import GameEvents.FillUpStorageEvent;
import GameMoves.Move;
import GameMoves.FillUpStorageMove;
import game.Game;

public class FillUpStorage implements Procedure {
  private FillUpStorageMove move;
  private Game game;
  private int playerId;

  FillUpStorage(Game game, int playerId) {
    this.game = game;
    this.playerId = playerId;
  }

  public void put(Move move) {
    this.move = (FillUpStorageMove) move;
  }

  public FillUpStorageEvent exec() {
    this.game.getPlayer(playerId).getSupplySled().addStones();

    return new FillUpStorageEvent(playerId, game.getPlayer(playerId).getSupplySled().getStones());
  }
}
