package game.gameprocedures.toolcardprotocols;

import events.app.game.ToolCardEvent;
import events.app.game.VoyageToStoneSiteExclusiveEvent;
import events.app.game.VoyageToStoneSiteManualDumpEvent;
import game.Game;
import game.gameprocedures.VoyageToStoneSiteManualDump;
import requests.gamemoves.CardType;
import requests.gamemoves.Move;
import requests.gamemoves.VoyageToStoneSiteManualDumpMove;
import requests.gamemoves.VoyageToStoneSiteMove;

public class LeverProtocol extends Protocol {

  public LeverProtocol(Game game, int playerId) {
    super(game, playerId);
  }

  public void exec() {
    game.sendAll(new ToolCardEvent(CardType.LEVER, playerId, true, game.getGameID()));
    game.sendTo(game.getPlayer(playerId).getUser(),
        new VoyageToStoneSiteExclusiveEvent(game.getGameID()));
    Move move = acquireMove();
    if (move instanceof VoyageToStoneSiteMove) {
      game.sendTo(game.getPlayer(playerId).getUser(),new VoyageToStoneSiteManualDumpEvent(game.getGameID(), ((VoyageToStoneSiteMove)move).getShipId()));
      Move voyManMove = acquireMove();
      if (voyManMove instanceof VoyageToStoneSiteManualDumpMove) {
        VoyageToStoneSiteManualDumpMove voy = (VoyageToStoneSiteManualDumpMove) voyManMove;
        VoyageToStoneSiteManualDump voyageToStoneSiteManualDump = new VoyageToStoneSiteManualDump(game, playerId,voy.getDumpOrder());
        voyageToStoneSiteManualDump.put(move);
        game.executeMove(move);
      }
    }
  }
}
