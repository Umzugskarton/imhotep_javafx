package game.GameProcedures;


import GameMoves.loadUpBoatMove;
import game.Game;

public class loadUpBoat {
  private loadUpBoatMove move;
  private Game game;
  private int playerId;

  loadUpBoat(loadUpBoatMove move, Game game, int playerId){
    this.game = game;
    this.move = move;
  }

  public void exec(){
    this.game.addStonesToStorage(this.playerId);
  }
}
