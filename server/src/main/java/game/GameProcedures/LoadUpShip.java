package game.GameProcedures;


import GameEvents.AlreadyAllocatedError;
import GameEvents.ShipLoadedEvent;
import GameEvents.fillUpStorageEvent;
import GameMoves.Move;
import GameMoves.loadUpShipMove;
import SRVevents.Event;
import game.Game;
import game.Player;
import game.board.Ship;
import game.board.Stone;

public class LoadUpShip implements Procedure {
  private loadUpShipMove move;
  private Game game;
  private int playerId;

  LoadUpShip(Game game, int playerId) {
    this.game = game;
    this.playerId = playerId;
  }

  public void put(Move move) {
    this.move = (loadUpShipMove) move;
  }

  public Event exec() {
    Player player = game.getOrder()[playerId];
    Stone stone = new Stone(player);
    Ship ship = game.getBoatByID(move.getShipId());

    if (ship.addStone(stone, move.getPosition())) {
      int[] shipInt = new int[ship.getStones().length];
      for (int i = 0; i < ship.getStones().length; i++) {
        if (ship.getStones()[i] != null) {
          shipInt[i] = ship.getStones()[i].getPlayer().getId();
        }
      }
      return new ShipLoadedEvent(move.getShipId(), shipInt);
    }
    else{
        return new AlreadyAllocatedError(move.getShipId(), move.getPosition());
      }
  }
}
