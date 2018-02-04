package game.gameprocedures;

import events.Event;
import events.SiteType;
import events.app.game.ChooseCardEvent;
import events.app.game.LocationCardEvent;
import events.app.game.NotEnoughLoadError;
import events.app.game.ShipAlreadyDockedError;
import events.app.game.ShipDockedEvent;
import events.app.game.SiteAlreadyDockedError;
import game.Game;
import game.board.Market;
import game.board.Ship;
import game.board.Stone;
import game.board.cards.Card;
import game.board.cards.LocationCard;
import java.util.ArrayList;
import java.util.List;
import requests.gamemoves.CardType;
import requests.gamemoves.Move;
import requests.gamemoves.VoyageToMarketMove;

public class VoyageToMarket implements Procedure {

  private VoyageToMarketMove move;
  private Game game;
  private int playerId;

  VoyageToMarket(Game game, int playerId) {
    this.game = game;
    this.playerId = playerId;
  }

  @Override
  public void put(Move move) {
    this.move = (VoyageToMarketMove) move;
  }

  @Override
  public Event exec() {
    Ship ship = game.getShipByID(move.getShipId());
    Market market = game.getMARKET();
    Card card;

    if (ship.isDocked()) {
      return new ShipAlreadyDockedError(move.getShipId());
    }
    int loadedStones = 0;
    for (Stone stone : ship.getStones()) {
      if (stone != null) {
        loadedStones++;
      }
    }

    if (loadedStones < ship.getMinimumStones()) {
      return new NotEnoughLoadError(move.getShipId());
    }

    if (!market.dockShip(ship)) {
      return new SiteAlreadyDockedError(SiteType.MARKET);
    }
    ship.setDocked(true);
    ArrayList<Integer> marketStones = new ArrayList<>();

    for (Stone stone : ship.getStones()) {
      //Spieler, die eine Stein auf dem Boot haben, können pro Stein eine Karte vom Markt wählen
      //TODO: Make me beautiful~
      card = playerCardSelect(stone.getPlayer().getId());
      if (card instanceof LocationCard) {
        Stone newStone = new Stone(stone.getPlayer());
        //Je nach Karte wird ein Stein aus dem Steinbruch auf den entsprechnenden Ort gesetzt
        if (card.getType() == CardType.ENTRANCE) {
          //TODO: Event einfügen
          game.getPYRAMID().addStone(newStone);
          LocationCardEvent e = new LocationCardEvent(0);
        } else if (card.getType() == CardType.SARCOPHAGUS) {
          game.getBURIALCHAMBER().addStone(newStone);
          LocationCardEvent e = new LocationCardEvent(1);
        } else if (card.getType() == CardType.PAVEDPATH) {
          game.getOBELISKS().addStone(newStone);
          LocationCardEvent e = new LocationCardEvent(2);
        }
      } else {
        stone.getPlayer().getInventory().addCard(card);
      }
      marketStones.add(stone.getPlayer().getId());
    }
    return new ShipDockedEvent(move.getShipId(), SiteType.MARKET, marketStones);
  }

  private Card playerCardSelect(int playerId) {
    List<Card> activeCards = game.getMARKET().getActiveCards();
    //TODO: Player-ID sucht sich eine Karte aus
    int choosenCard = 0;
    ChooseCardEvent chooseCardEvent = new ChooseCardEvent(playerId, choosenCard);
    game.sendAll(chooseCardEvent);
    return activeCards.get(choosenCard);
  }
}