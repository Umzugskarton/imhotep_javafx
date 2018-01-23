package game.GameProcedures.ToolCardProtocols;

import GameEvents.LoadUpShipExclusiveEvent;
import GameEvents.ToolCardEvent;
import GameMoves.LoadUpShipMove;
import GameMoves.Move;
import GameMoves.VoyageToStoneSiteMove;
import game.Game;

public class SailProtocol implements Protocol {
  private Game game;
  private int playerId;

  public SailProtocol(Game game , int playerId){
    this.game=game;
    this.playerId=playerId;
  }

  public void exec(){
    game.sendAll(new ToolCardEvent("Sail", playerId ,true));
    game.sendTo(game.getOrder()[playerId].getUser(), new LoadUpShipExclusiveEvent());

    for (int i = 0; i < 2 ; i++){
      int tries  =0;
      while (game.getExecutor().getMove() == null && !(game.getExecutor().getMove() instanceof ) && tries < 2) {
        game.getExecutor().waitForMove();
        tries++;
      }
      Move move = game.getExecutor().getMove();
      game.executeMove(move);
      }
    }

  private void waitForMove
}



