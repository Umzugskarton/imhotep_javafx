package game.gameprocedures.toolcardprotocols;

import events.app.game.ToolCardEvent;
import events.app.game.VoyageToStoneSiteManualDumpEvent;
import game.Game;
import requests.gamemoves.CardType;
import requests.gamemoves.Move;
import requests.gamemoves.VoyageToStoneSiteManualDumpMove;

public class LeverProtocol extends Protocol {

  public LeverProtocol(Game game, int playerId) {
    super(game, playerId);
  }

  public void exec() {
    game.sendAll(new ToolCardEvent(CardType.LEVER, playerId, true, game.getGameID()));
    game.sendTo(game.getPlayer(playerId).getUser(), new VoyageToStoneSiteManualDumpEvent(game.getGameID()));
    Move move = acquireMove();
    if (move instanceof VoyageToStoneSiteManualDumpMove) {
      game.executeMove(move);
    }
  }
}
