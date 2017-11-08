package game.GameProcedures;



import GameMoves.fillUpStorageMove;
import game.Game;

public class fillUpStorage {
  private fillUpStorageMove move;
  private Game game;
  private int playerId;

  fillUpStorage(fillUpStorageMove move, Game game, int playerId){
    this.game = game;
    this.move = move;
  }

  public void exec(){
    this.game.addStonesToStorage(this.playerId);
  }
}
