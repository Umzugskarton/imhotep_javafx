package game.GameProcedures.ToolCardProtocols;

import GameEvents.ToolCardEvent;
import GameEvents.VoyageToStoneSiteExclusiveEvent;
import GameMoves.FillUpStorageMove;
import GameMoves.Move;
import GameMoves.VoyageToStoneSiteMove;
import game.Game;

public class HammerProtocol extends Protocol {

  public HammerProtocol(Game game, int playerId) {
    super(game, playerId);
  }

  public void exec() {
    game.sendAll(new ToolCardEvent("Hammer", playerId, true));
    game.executeMove(new FillUpStorageMove());
    game.sendTo(game.getOrder()[playerId].getUser(), new VoyageToStoneSiteExclusiveEvent());
    Move move = acquireMove();
    if (move instanceof VoyageToStoneSiteMove) {
      game.executeMove(move);
    }
  }
}
