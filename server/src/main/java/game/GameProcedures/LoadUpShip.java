package game.GameProcedures;

import events.app.game.AlreadyAllocatedError;
import events.app.game.ShipLoadedEvent;
import requests.GameMoves.Move;
import requests.GameMoves.LoadUpShipMove;
import events.Event;
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
    if (game.getPlayer(playerId).getSupplySled().removeStone()) {
      Player player = game.getPlayer(playerId);
      Stone stone = new Stone(player);
      Ship ship = game.getShipByID(move.getShipId());

      if (ship.addStone(stone, move.getPosition())) {
        return new ShipLoadedEvent(playerId, move.getShipId(), game.getCargoAsIntArrayByShip(ship),
            game.getPlayer(playerId).getSupplySled().getStones());
      } else {
        return new AlreadyAllocatedError(move.getShipId(), move.getPosition());
      }
    }
    return new AlreadyAllocatedError(move.getShipId(), move.getPosition());
  }
}
