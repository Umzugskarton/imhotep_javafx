package game.GameProcedures;

import GameMoves.Move;
import GameMoves.voyageToMarketMove;
import GameMoves.voyageToStoneSiteMove;
import SRVevents.Event;
import game.Game;
import game.board.Market;
import game.board.Ship;
import game.board.StoneSite;

import java.lang.reflect.Method;

/**
 * Created on 16.12.2017.
 */
public class VoyageToMarket {
  private voyageToMarketMove move;
  private Game game;
  private int playerId;

  VoyageToMarket(Game game, int playerId) {
    this.game = game;
    this.playerId = playerId;
  }

  public void put(Move move) {
    this.move = (voyageToStoneSiteMove) move;
  }

  public Event exec() {
    Ship ship = game.getBoatbyID(move.getShipId());
    Market market = game.getMarket();
    Method method;

    if (!ship.isDocked()) {
      if (market.dockShip(ship)){
        ship.setDocked(true);
       //TODO MARKET CARDS ETC.
      }
      else {
        return new SiteAlreadyDockedErrorEvent(move.getShipId());
      }

    } else {
      return new ShipAlreadyDockedErrorEvent(move.getShipId());
    }
  }
}
