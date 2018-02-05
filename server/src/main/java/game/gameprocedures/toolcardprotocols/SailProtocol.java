package game.gameprocedures.toolcardprotocols;

import events.app.game.LoadUpShipExclusiveEvent;
import events.app.game.ToolCardEvent;
import events.app.game.VoyageToStoneSiteExclusiveEvent;
import game.Game;
import requests.gamemoves.CardType;
import requests.gamemoves.LoadUpShipMove;
import requests.gamemoves.Move;
import requests.gamemoves.VoyageToStoneSiteMove;

public class SailProtocol extends Protocol {

  public SailProtocol(Game game, int playerId) {
    super(game, playerId);
  }

  public void exec() {
    game.sendAll(new ToolCardEvent(CardType.SAIL, playerId, true, game.getGameID()));

    Move move;
    game.sendTo(game.getPlayer(playerId).getUser(), new LoadUpShipExclusiveEvent(game.getGameID()));
    move = acquireMove();
    if (move instanceof LoadUpShipMove) {
      game.executeMove(move);
    }

    game.sendTo(game.getPlayer(playerId).getUser(), new VoyageToStoneSiteExclusiveEvent(game.getGameID()));
    move = acquireMove();
    if (move instanceof VoyageToStoneSiteMove) {
      game.executeMove(move);
    }
  }
}

