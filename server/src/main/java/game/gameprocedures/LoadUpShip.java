package game.gameprocedures;

import events.Event;
import events.app.game.AlreadyAllocatedError;
import events.app.game.ShipLoadedEvent;
import game.Game;
import game.Player;
import game.board.Ship;
import game.board.Stone;
import requests.gamemoves.LoadUpShipMove;
import requests.gamemoves.Move;

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
    if (game.getPlayer(playerId).removeStone()) {
      Player player = game.getPlayer(playerId);
      Stone stone = new Stone(player);
      Ship ship = game.getShipByID(move.getShipId());

      if (ship.addStone(stone, move.getPosition())) {
        return new ShipLoadedEvent(playerId, move.getShipId(), game.getCargoAsIntArrayByShip(ship),
            game.getPlayer(playerId).getStones());
      }
    }
    // TODO kann auch ein anderer Fehler als AlreadyAllocated sein: z.B. Position nicht vorhanden
    return new AlreadyAllocatedError(move.getShipId(), move.getPosition());
  }
}
