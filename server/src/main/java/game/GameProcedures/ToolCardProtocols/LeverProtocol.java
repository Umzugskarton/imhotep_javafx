package game.GameProcedures.ToolCardProtocols;

import game.Game;

public class LeverProtocol implements Protocol {
  private Game game;
  private int playerId;

  public LeverProtocol(Game game , int playerId){
    this.game=game;
    this.playerId=playerId;
  }

  public void exec(){

  }
}
