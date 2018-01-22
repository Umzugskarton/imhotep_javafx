package game.GameProcedures.ToolCardProtocols;

import GameEvents.ChiselCardEvent;
import GameEvents.LoadUpShipExclusiveEvent;
import GameMoves.LoadUpShipMove;
import GameMoves.Move;
import game.Game;

public class ChiselProtocol implements Protocol {
  private int playerId;
  private Game game;

  public ChiselProtocol (Game game , int playerId){
   this.game = game;
   this.playerId = playerId;
  }

  public void exec(){
    game.sendAll(new ChiselCardEvent(playerId));
    for (int i = 0; i < 2; i++) {
      game.sendTo(game.getOrder()[playerId].getUser(), new LoadUpShipExclusiveEvent());
      int tries = 0;
      while (game.getExecutor().getMove() == null && !(game.getExecutor().getMove() instanceof LoadUpShipMove) && tries < 2) {
        game.getExecutor().waitForMove();
        Move move = game.getExecutor().getMove();
        game.executeMove(move);
        tries++;
      }
    }
  }
}
