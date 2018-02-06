package game.gameprocedures;

import events.Event;
import events.app.game.AlreadyAllocatedError;
import events.app.game.OutOfStonesError;
import events.app.game.PositionInvalidError;
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

  /**
   * Belädt ein Schiff und entfernt einen Stein aus dem Vorrat des anfragenden Spielers sollte dieser genug Steine besitzen
   * uund der Platz auf dem Schiff nicht schon belegt sein
   *
   * @return Event, der den jeweieligen Status des Spielzugs beschreibt
   */

  public Event exec() {
    if (game.getPlayer(playerId).getStones() <= 0) {
      return new OutOfStonesError(playerId, game.getGameID());
    }
    if (game.getPlayer(playerId).removeStone()) {
      Player player = game.getPlayer(playerId);
      Stone stone = new Stone(player);
      Ship ship = game.getShipByID(move.getShipId());

      if (ship.addStone(stone, move.getPosition())) {
        return new ShipLoadedEvent(playerId, move.getShipId(), ship.getCargoAsIntArrayByShip(),
            game.getPlayer(playerId).getStones(), game.getGameID());
      } else {
        game.getPlayer(playerId).addStones(1);
        return new PositionInvalidError(game.getGameID());
      }
    }
    return new AlreadyAllocatedError(move.getShipId(), move.getPosition(), game.getGameID());
  }
}