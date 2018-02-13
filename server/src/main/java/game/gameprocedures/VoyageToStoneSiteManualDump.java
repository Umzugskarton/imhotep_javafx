package game.gameprocedures;

import events.Event;
import events.app.game.DockingShipError;
import events.app.game.NotEnoughLoadError;
import events.app.game.ShipAlreadyDockedError;
import events.app.game.ShipDockedEvent;
import events.app.game.SiteAlreadyDockedError;
import game.Game;
import game.board.Ship;
import game.board.Site;
import game.board.Stone;
import java.util.ArrayList;
import requests.gamemoves.Move;
import requests.gamemoves.VoyageMove;
import requests.gamemoves.VoyageToMarketMove;
import requests.gamemoves.VoyageToStoneSiteMove;

public class VoyageToStoneSiteManualDump extends Voyage {

  private VoyageMove move;
  private Game game;
  private int[] dumpOrder;

  public VoyageToStoneSiteManualDump(Game game, int[] dumpOrder) {
    this.game = game;
    this.dumpOrder = dumpOrder;
  }

  public void put(Move move) {
    this.move = (VoyageMove) move;
  }

  public Event exec() {

    Ship ship = game.getShipByID(move.getShipId());
    Site site;
    try {
      site = game.getSiteByType(move.getSiteType());
    } catch (Exception e) {
      return new DockingShipError(game.getGameID());
    }

    if (!ship.isDocked()) {
      if (ship.getLoadedStones() >= ship.getMinimumStones()) {
        Stone[] stones = ship.sortStones(dumpOrder);
        if (move instanceof VoyageToStoneSiteMove) {
          if (site.dockShip(ship)) {
            ship.setDocked(true);
            ArrayList<Integer> siteStones = new ArrayList<>();
            for (int i = 0; i < stones.length; i++) {
              siteStones.add(stones[i].getPlayer().getId());
            }
            return new ShipDockedEvent(move.getShipId(), move.getSiteType(),
                siteStones, game.getGameID());
          } else {
            return new SiteAlreadyDockedError(move.getSiteType());
          }
        } else if (move instanceof VoyageToMarketMove) {
          VoyageToMarket voyageToMarket = new VoyageToMarket(game);
          voyageToMarket.put(move);
          return voyageToMarket.exec();
        }
        else{
          return new NotEnoughLoadError(move.getShipId(), game.getGameID());
        }
      } else {
        return new NotEnoughLoadError(move.getShipId(), game.getGameID());
      }
    } else {
      return new ShipAlreadyDockedError(move.getShipId(), game.getGameID());
    }
  }
}
