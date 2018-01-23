package game.GameProcedures.ToolCardProtocols;

import GameEvents.ToolCardEvent;
import GameEvents.VoyageToStoneSiteExclusiveEvent;
import GameMoves.FillUpStorageMove;
import GameMoves.Move;
import GameMoves.VoyageToStoneSiteMove;
import game.Game;

public class HammerProtocol implements Protocol {
  private Game game;
  private int playerId;

  public HammerProtocol(Game game, int playerId) {
    this.game = game;
    this.playerId = playerId;
  }

  public void exec() {
    game.sendAll(new ToolCardEvent("Hammer", playerId, true));
    Move move;
    game.executeMove(new FillUpStorageMove());
    game.sendTo(game.getOrder()[playerId].getUser(), new VoyageToStoneSiteExclusiveEvent());
    move = acquireMove();
    if (move instanceof VoyageToStoneSiteMove) {
      game.executeMove(move);
    }
  }

  private Move acquireMove() {
    game.getExecutor().waitForMove();
    if (game.getExecutor().getMove() != null)
      return game.getExecutor().getMove();
    return null;
  }
}
