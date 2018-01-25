package game.GameProcedures.ToolCardProtocols;

import GameEvents.ToolCardEvent;
import GameEvents.VoyageToStoneSiteManualDumpEvent;
import GameMoves.Move;
import GameMoves.VoyageToStoneSiteManualDumpMove;
import game.Game;

public class LeverProtocol extends Protocol {

  public LeverProtocol(Game game , int playerId){
    super(game, playerId);
  }

  public void exec(){
    game.sendAll(new ToolCardEvent("Lever", playerId, true));
    game.sendTo(game.getPlayer(playerId).getUser(), new VoyageToStoneSiteManualDumpEvent());
    Move move = acquireMove();
    if (move instanceof VoyageToStoneSiteManualDumpMove) {
      game.executeMove(move);
    }
  }
}
