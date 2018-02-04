package game.gameprocedures;

import events.Event;
import events.app.game.*;
import game.Game;
import game.board.Ship;
import game.board.Stone;
import game.board.StoneSite;
import requests.gamemoves.Move;
import requests.gamemoves.VoyageToStoneSiteMove;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class VoyageToStoneSite implements Procedure {
  private VoyageToStoneSiteMove move;
  private Game game;
  private int playerId;

  VoyageToStoneSite(Game game, int playerId) {
    this.game = game;
    this.playerId = playerId;
  }

  public void put(Move move) {
    this.move = (VoyageToStoneSiteMove) move;
  }

  public Event exec() {
    Ship ship = game.getShipByID(move.getShipId());
    StoneSite site;
    Method method;

    try {
      method = game.getClass().getMethod("get" + move.getStoneSite().toString());
      site = (StoneSite) method.invoke(game);
    } catch (Exception e) {
      return new DockingShipError();
    }

    if (!ship.isDocked()) {
      if (ship.getLoadedStones() >= ship.getMinimumStones()) {
        if (site.dockShip(ship)) {
          ship.setDocked(true);
          ArrayList<Integer> siteStones = new ArrayList<>();

          for (Stone stone : site.getStones()) {
            if (stone != null) {
              siteStones.add(stone.getPlayer().getId());
            }
          }
          if (move.getStoneSite().toString().equals("PYRAMIDS")) {
            game.updatePyramids();
          }
          return new ShipDockedEvent(move.getShipId(), move.getStoneSite(), siteStones);
        } else {
          return new SiteAlreadyDockedError(move.getStoneSite());
        }
      } else {
        return new NotEnoughLoadError(move.getShipId());
      }
    }else {
      return new ShipAlreadyDockedError(move.getShipId());
    }
  }
}
