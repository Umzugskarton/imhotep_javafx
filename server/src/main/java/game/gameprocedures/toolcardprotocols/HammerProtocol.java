package game.gameprocedures.toolcardprotocols;

import events.app.game.ToolCardEvent;
import events.app.game.VoyageToStoneSiteExclusiveEvent;
import game.Game;
import requests.gamemoves.CardType;
import requests.gamemoves.FillUpStorageMove;
import requests.gamemoves.Move;
import requests.gamemoves.VoyageToStoneSiteMove;

public class HammerProtocol extends Protocol {

  public HammerProtocol(Game game, int playerId) {
    super(game, playerId);
  }

  public void exec() {
    game.sendAll(new ToolCardEvent(CardType.HAMMER, playerId, true));
    game.executeMove(new FillUpStorageMove(game.getGameID()));
    game.sendTo(game.getPlayer(playerId).getUser(), new VoyageToStoneSiteExclusiveEvent());
    Move move = acquireMove();
    if (move instanceof VoyageToStoneSiteMove) {
      game.executeMove(move);
    }
  }
}
