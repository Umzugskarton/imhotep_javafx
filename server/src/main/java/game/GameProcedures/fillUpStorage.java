package game.GameProcedures;



import GameMoves.Move;
import game.Game;

public class fillUpStorage implements Procedure {
  private Move move;
  private Game game;
  private int playerId;

  fillUpStorage(Move move, Game game, int playerId){
    this.game = game;
    this.move = move;
  }

  public void exec(){
    this.game.addStonesToStorage(this.playerId);
  }
}
