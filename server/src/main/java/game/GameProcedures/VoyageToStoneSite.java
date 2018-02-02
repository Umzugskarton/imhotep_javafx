package game.GameProcedures;

import events.app.game.DockingShipError;
import events.app.game.NotEnoughLoadError;
import events.app.game.ShipAlreadyDockedError;
import events.app.game.ShipDockedEvent;
import events.app.game.SiteAlreadyDockedError;
import requests.gamemoves.Move;
import requests.gamemoves.VoyageToStoneSiteMove;
import events.Event;
import game.Game;
import game.board.Ship;
import game.board.Stone;
import game.board.StoneSite;
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
      method = game.getClass().getMethod("get" + move.getStonesite());
      site = (StoneSite) method.invoke(game);
    } catch (Exception e) {
      return new DockingShipError();
    }

    if (!ship.isDocked()) {
      int loadedStones = 0;
      for (Stone stone : ship.getStones()) {
        if (stone != null) {
          loadedStones++;
        }
      }
      if (loadedStones >= ship.getMinimumStones()) {
        if (site.dockShip(ship)) {
          ship.setDocked(true);
          ArrayList<Integer> siteStones = new ArrayList<>();

          for (Stone stone : site.getStones()) {
            if (stone != null) {
              siteStones.add(stone.getPlayer().getId());
            }
          }
          if (move.getStonesite().equals("Pyramids")) {
            game.updatePyramids();
          }
          return new ShipDockedEvent(move.getShipId(), move.getStonesite(), siteStones);
        } else {
          return new SiteAlreadyDockedError(move.getStonesite());
        }
      } else {
        return new NotEnoughLoadError(move.getShipId());
      }
    }else {
      return new ShipAlreadyDockedError(move.getShipId());
    }
  }
}
