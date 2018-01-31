package game.GameProcedures;

import GameEvents.*;
import GameMoves.CardType;
import GameMoves.Move;
import GameMoves.VoyageToMarketMove;
import SRVevents.Event;
import game.Game;
import game.board.Cards.Card;
import game.board.Cards.LocationCard;
import game.board.Market;
import game.board.Ship;
import game.board.Stone;
import game.board.StoneSite;

import java.lang.reflect.Method;
import java.util.ArrayList;

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

  public Event exec() {
    Ship ship = game.getShipByID(move.getShipId());
    Market market = game.getMarket();
    Card card;
    Method method;


    if(!ship.isDocked()) {
      int loadedStones = 0;
      for (Stone stone : ship.getStones()) {
        if (stone != null) {
          loadedStones++;
        }
      }

      if (loadedStones >= ship.getMinimumStones()) {
        if(market.dockShip(ship)) {
          ship.setDocked(true);
          ArrayList<Integer> marketStones = new ArrayList<>();

          for(Stone stone : ship.getStones()) {
              //Spieler, die eine Stein auf dem Boot haben, können pro Stein eine Karte vom Markt wählen
              card = playerCardSelect(stone.getPlayer().getId());
              if(card instanceof LocationCard) {
                Stone newStone = new Stone(stone.getPlayer());
                //Je nach Karte wird ein Stein aus dem Steinbruch auf den entsprechnenden Ort gesetzt
                if(card.getType() == CardType.Type.ENTRANCE) {
                  game.getPyramids().addStone(newStone);
                } else if(card.getType() == CardType.Type.SARCOPHAGUS) {
                  game.getBurialChamber().addStone(newStone);
                } else if(card.getType() == CardType.Type.PAVEDPATH) {
                  game.getObelisks().addStone(newStone);
                }
              } else {
                stone.getPlayer().getInventory().addCard(card);
              }
            marketStones.add(stone.getPlayer().getId());
          }
          return new ShipDockedEvent(move.getShipId(), market.toString(), marketStones);
        } else {
          return new SiteAlreadyDockedError("Market");
        }
      } else {
        return new NotEnoughLoadError(move.getShipId());
      }
    } else {
      return new ShipAlreadyDockedError(move.getShipId());
    }
  }

  private Card playerCardSelect(int playerId) {
    //TODO: Player-ID sucht sich eine Karte aus
    Card card = null;
    return card;
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
