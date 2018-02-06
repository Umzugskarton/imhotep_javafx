package game.gameprocedures;

import events.Event;
import events.SiteType;
import events.app.game.*;
import game.Game;
import game.board.Ship;
import game.board.Stone;
import game.board.StoneSite;
import requests.gamemoves.Move;
import requests.gamemoves.VoyageToStoneSiteManualDumpMove;

import java.util.ArrayList;

public class VoyageToStoneSiteManualDump extends Voyage {

  private VoyageToStoneSiteManualDumpMove move;
  private Game game;
  private int playerId;
  private int lobbyId;

  VoyageToStoneSiteManualDump(Game game, int playerId) {
    this.game = game;
    this.playerId = playerId;
  }

  public void put(Move move) {
    this.move = (VoyageToStoneSiteManualDumpMove) move;
  }

  public Event exec() {
    Ship ship = game.getShipByID(move.getShipId());
    StoneSite site;
    try {
      site = (StoneSite) game.getSiteByType(move.getStoneSite());
    } catch (Exception e) {
      return new DockingShipError(game.getGameID());
    }

    if (!ship.isDocked()) {
      if (ship.getLoadedStones() >= ship.getMinimumStones()) {
        if (site.dockShip(ship)) {
          ship.setDocked(true);
          ArrayList<Integer> siteStones = new ArrayList<>();
          Stone[] stones = ship.sortStones(move.getDumpOrder());
          for(int i = 0; i < stones.length; i++) {
            siteStones.add(stones[i].getPlayer().getId());
          }
          return new ShipDockedEvent(move.getShipId(), move.getStoneSite(),
                  siteStones, game.getGameID());
        } else {
          return new SiteAlreadyDockedError(move.getStoneSite());
        }
      } else {
        return new NotEnoughLoadError(move.getShipId(), game.getGameID());
      }
    } else {
      return new ShipAlreadyDockedError(move.getShipId(), game.getGameID());
    }
  }

}
