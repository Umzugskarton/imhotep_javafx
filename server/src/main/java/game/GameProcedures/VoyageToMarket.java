package game.GameProcedures;

import GameMoves.Move;
import GameMoves.VoyageToMarketMove;
import game.Game;

public class VoyageToMarket {
  private VoyageToMarketMove move;
  private Game game;
  private int playerId;

  VoyageToMarket(Game game, int playerId) {
    this.game = game;
    this.playerId = playerId;
  }

  public void put(Move move) {
    this.move = (VoyageToMarketMove) move;
  }
  /*

  public Event exec() {
  /*  Ship ship = game.getBoatbyID(move.getShipId());
    Market market = game.getMarket();
    Method method;

    if (!ship.isDocked()) {
      if (market.dockShip(ship)){
        ship.setDocked(true);
       //TODO MARKET CARDS ETC.
      }
      else {
        return new SiteAlreadyDockedError("Market");
      }

    } else {
      return new ShipAlreadyDockedError(move.getShipId());
    }
  }*/
}
