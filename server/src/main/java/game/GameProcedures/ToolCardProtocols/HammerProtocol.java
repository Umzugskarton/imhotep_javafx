package game.GameProcedures.ToolCardProtocols;

import events.app.game.ToolCardEvent;
import events.app.game.VoyageToStoneSiteExclusiveEvent;
import requests.GameMoves.CardType.Type;
import requests.GameMoves.FillUpStorageMove;
import requests.GameMoves.Move;
import requests.GameMoves.VoyageToStoneSiteMove;
import game.Game;

public class HammerProtocol extends Protocol {

  public HammerProtocol(Game game, int playerId) {
    super(game, playerId);
  }

  public void exec() {
    game.sendAll(new ToolCardEvent(Type.HAMMER, playerId, true));
    game.executeMove(new FillUpStorageMove());
    game.sendTo(game.getPlayer(playerId).getUser(), new VoyageToStoneSiteExclusiveEvent());
    Move move = acquireMove();
    if (move instanceof VoyageToStoneSiteMove) {
      game.executeMove(move);
    }
  }
}
