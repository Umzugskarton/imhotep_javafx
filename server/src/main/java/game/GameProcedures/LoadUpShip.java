package game.GameProcedures;


import GameEvents.AlreadyAllocatedError;
import GameEvents.ShipLoadedEvent;
import GameMoves.Move;
import GameMoves.LoadUpShipMove;
import SRVevents.Event;
import game.Game;
import game.Player;
import game.board.Ship;
import game.board.Stone;

public class LoadUpShip implements Procedure {

  private LoadUpShipMove move;
  private Game game;
  private int playerId;

  LoadUpShip(Game game, int playerId) {
    this.game = game;
    this.playerId = playerId;
  }

  public void put(Move move) {
    this.move = (LoadUpShipMove) move;
  }

  public Event exec() {
    if (game.getOrder()[playerId].getSupplySled().removeStone()) {
      Player player = game.getOrder()[playerId];
      Stone stone = new Stone(player);
      Ship ship = game.getShipByID(move.getShipId());

      if (ship.addStone(stone, move.getPosition())) {
        return new ShipLoadedEvent(playerId, move.getShipId(), game.getCargoAsIntArrayByShip(ship),
            game.getOrder()[playerId].getSupplySled().getStones());
      } else {
        return new AlreadyAllocatedError(move.getShipId(), move.getPosition());
      }
    }
    return new AlreadyAllocatedError(move.getShipId(), move.getPosition());
  }
}
