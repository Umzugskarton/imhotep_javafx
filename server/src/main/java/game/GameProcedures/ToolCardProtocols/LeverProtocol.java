package game.GameProcedures.ToolCardProtocols;

import events.app.game.ToolCardEvent;
import events.app.game.VoyageToStoneSiteManualDumpEvent;
import requests.gamemoves.CardType.Type;
import requests.gamemoves.Move;
import requests.gamemoves.VoyageToStoneSiteManualDumpMove;
import game.Game;

public class LeverProtocol extends Protocol {

  public LeverProtocol(Game game, int playerId) {
    super(game, playerId);
  }

  public void exec() {
    game.sendAll(new ToolCardEvent(Type.LEVER, playerId, true));
    game.sendTo(game.getPlayer(playerId).getUser(), new VoyageToStoneSiteManualDumpEvent());
    Move move = acquireMove();
    if (move instanceof VoyageToStoneSiteManualDumpMove) {
      game.executeMove(move);
    }
  }
}
