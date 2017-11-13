package game.GameProcedures;



import GameEvents.fillUpStorageEvent;
import GameMoves.Move;
import GameMoves.fillUpStorageMove;
import SRVevents.Event;
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

  public fillUpStorageEvent exec(){
    this.game.addStonesToStorage(playerId);

    return new fillUpStorageEvent(playerId, game.getStorage(playerId));
  }
}
