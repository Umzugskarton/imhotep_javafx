package game.gameprocedures;

import events.Event;
import events.SiteType;
import events.app.game.*;
import game.Game;
import game.board.Ship;
import game.board.Stone;
import game.board.StoneSite;
import requests.gamemoves.Move;
import requests.gamemoves.VoyageToStoneSiteMove;

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

  /**
   * Berechnet die Punkte, der OrnamentCards, besitzt ein Spieler eine OrnamentCard
   * so wird über dessen Typ und mit dem stones Integer Array die Punktezahl zusammen gerechnet die dieser
   * für jene OrnamentCard erhält , sollte er mehrere besitzen werden die punkte kumuliert und zurückgegeben
   *
   * @param player die Id des Spielers im Game
   * @return points, die der Spieler durch die OrnamentCards erhält
   */

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

          for (Stone stone : site.getStones()) {
            if (stone != null) {
              siteStones.add(stone.getPlayer().getId());
            }
          }
          if (move.getStoneSite() == SiteType.PYRAMID) {
            game.updatePyramids();
          }
          return new ShipDockedEvent(move.getShipId(), move.getStoneSite(), siteStones,
              game.getGameID());
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
