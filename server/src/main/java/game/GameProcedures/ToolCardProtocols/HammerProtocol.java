package game.GameProcedures.ToolCardProtocols;

import game.Game;

public class HammerProtocol implements Protocol{
  private Game game;
  private int playerId;

  public HammerProtocol(Game game , int playerId){
    this.game=game;
    this.playerId=playerId;
  }

  public void exec(){

  }
}
