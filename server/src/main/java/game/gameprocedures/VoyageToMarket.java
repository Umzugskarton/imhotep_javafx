package game.gameprocedures;

import events.Event;
import events.SiteType;
import events.app.game.ChooseCardEvent;
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
import requests.gamemoves.ChooseCardMove;
import requests.gamemoves.Move;
import requests.gamemoves.VoyageToMarketMove;

public class VoyageToMarket implements Procedure{
    private VoyageToMarketMove move;
    private Game game;
    private Market market;

    VoyageToMarket(Game game) {
        this.game = game;
        this.market = (Market) game.getSiteByType(SiteType.MARKET);
    }

  @Override
  public void put(Move move) {
    this.move = (VoyageToMarketMove) move;
  }

    public Event exec() {
        Ship ship = game.getShipByID(move.getShipId());
        if (!ship.isDocked()) {
            if (ship.getLoadedStones() >= ship.getMinimumStones()) {
                if (market.dockShip(ship)) {
                    ship.setDocked(true);
                    return new ShipDockedEvent(move.getShipId(), SiteType.MARKET, doMarketRotation(ship.getStones()));
                } else {
                    return new SiteAlreadyDockedError(SiteType.MARKET);
                }
            } else {
                return new NotEnoughLoadError(move.getShipId());
            }
        } else {
            return new ShipAlreadyDockedError(move.getShipId());
        }
    }

    private ArrayList<Integer> doMarketRotation(Stone[] stones){
        List<Card> activeCards = market.getActiveCards();
        ArrayList<Integer> chosenCards = new ArrayList<>();
      for (Stone stone : stones) {
        game.sendTo(game.getPlayer(stone.getPlayer().getId()).getUser(), new ChooseCardEvent(game.getGameID() , chosenCards));
        Move move = acquireMove();
        if ( move instanceof ChooseCardMove) {
          ChooseCardMove chooseCard = (ChooseCardMove)move;
          int cardId = chooseCard.getCardId();
          chosenCards.add(cardId);
          Card card = activeCards.get(cardId);
          market.removeCard(cardId);
          if (card instanceof LocationCard) {
            ((LocationCard) card).exec(game , stone.getPlayer().getId());
          } else {
            stone.getPlayer().getInventory().addCard(card);
          }
        }
      }
      return chosenCards;
    }

  private Move acquireMove() {
    game.getExecutor().waitForMove();
    if (game.getExecutor().getMove() != null) {
      return game.getExecutor().getMove();
    }
    return null;
  }
}