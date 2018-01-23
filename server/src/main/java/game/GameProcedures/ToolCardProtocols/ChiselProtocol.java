package game.GameProcedures.ToolCardProtocols;

import GameEvents.LoadUpShipExclusiveEvent;
import GameEvents.ToolCardEvent;
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
    game.sendAll(new ToolCardEvent("Chisel", playerId, true));
    for (int i = 0; i < 2; i++) {
      game.sendTo(game.getOrder()[playerId].getUser(), new LoadUpShipExclusiveEvent());
      int tries = 0;
      while (game.getExecutor().getMove() == null && !(game.getExecutor().getMove() instanceof LoadUpShipMove) && tries < 2) {
        game.getExecutor().waitForMove();
        tries++;
      }
      Move move = game.getExecutor().getMove();
      game.executeMove(move);
    }
  }
}
