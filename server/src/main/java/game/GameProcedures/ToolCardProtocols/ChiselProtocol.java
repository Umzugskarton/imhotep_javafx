package game.GameProcedures.ToolCardProtocols;

import GameEvents.LoadUpShipExclusiveEvent;
import GameEvents.OutOfStonesError;
import GameEvents.ToolCardEvent;
import GameMoves.LoadUpShipMove;
import GameMoves.Move;
import game.Game;

public class ChiselProtocol extends Protocol {

  public ChiselProtocol(Game game, int playerId) {
    super(game, playerId);
  }

  public void exec() {
    game.sendAll(new ToolCardEvent("Chisel", playerId, true));
    for (int i = 0; i < 2; i++) {
      game.sendTo(game.getOrder()[playerId].getUser(), new LoadUpShipExclusiveEvent());
      if (game.getStorage(playerId) > 0) {
        game.getExecutor().waitForMove();
        Move move = acquireMove();
        if (move instanceof LoadUpShipMove)
          game.executeMove(move);
      } else {
        game.sendTo(game.getOrder()[playerId].getUser(), new OutOfStonesError(playerId));
        break;
      }
    }
  }

}
