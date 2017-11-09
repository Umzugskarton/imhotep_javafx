package game.GameProcedures;



import GameMoves.Move;
import GameMoves.fillUpStorageMove;
import game.Game;

public class fillUpStorage implements Procedure {
  private fillUpStorageMove move;
  private Game game;
  private int playerId;

  fillUpStorage(Game game, int playerId){
    this.game = game;
    this.move = move;
    this.playerId = playerId;
  }

  public void put(Move move){
    this.move = (fillUpStorageMove) move;
  }

  public void exec(){
    this.game.addStonesToStorage(this.playerId);
  }
}
