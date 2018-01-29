package game.GameProcedures.ToolCardProtocols;

import GameEvents.LoadUpShipExclusiveEvent;
import GameEvents.OutOfStonesError;
import GameEvents.ToolCardEvent;
import GameMoves.CardType.Type;
import GameMoves.LoadUpShipMove;
import GameMoves.Move;
import game.Game;

public class ChiselProtocol extends Protocol {

  public ChiselProtocol(Game game, int playerId) {
    super(game, playerId);
  }

  public void exec() {
    game.sendAll(new ToolCardEvent(Type.CHISEL, playerId, true));
    for (int i = 0; i < 2; i++) {
      game.sendTo(game.getPlayer(playerId).getUser(), new LoadUpShipExclusiveEvent());
      if (game.getPlayer(playerId).getSupplySled().getStones() > 0) {
        game.getExecutor().waitForMove();
        Move move = acquireMove();
        if (move instanceof LoadUpShipMove)
          game.executeMove(move);
      } else {
        game.sendTo(game.getPlayer(playerId).getUser(), new OutOfStonesError(playerId));
        break;
      }
    }
  }

}
