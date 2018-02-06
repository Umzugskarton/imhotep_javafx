package game.gameprocedures;

import events.Event;
import events.SiteType;
import events.app.game.*;
import game.Game;
import game.board.Market;
import game.board.Ship;
import game.board.Stone;
import game.board.cards.Card;
import game.board.cards.LocationCard;
import requests.gamemoves.CardType;
import requests.gamemoves.ChooseCardMove;
import requests.gamemoves.Move;
import requests.gamemoves.VoyageToMarketMove;

import java.util.ArrayList;
import java.util.List;

public class VoyageToMarket implements Procedure {

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

  @Override
  public Event exec() {
    Ship ship = game.getShipByID(move.getShipId());
    if (!ship.isDocked()) {
      if (ship.getLoadedStones() >= ship.getMinimumStones()) {
        if (market.dockShip(ship)) {
          ship.setDocked(true);
          doMarketRotation(ship.getStones());
          return new ShipDockedEvent(move.getShipId(), SiteType.MARKET,
              null, game.getGameID());
        } else {
          return new SiteAlreadyDockedError(SiteType.MARKET);
        }
      } else {
        return new NotEnoughLoadError(move.getShipId(), game.getGameID());
      }
    } else {
      return new ShipAlreadyDockedError(move.getShipId(), game.getGameID());
    }
  }

  private void doMarketRotation(Stone[] stones) {
    List<Card> activeCards = market.getActiveCards();
    ArrayList<ArrayList<CardType>> cardTypes = new ArrayList<>();
    int[] chosenCards= new int[stones.length];
    for (int i = 0; i < game.getSize(); i++) {
      cardTypes.add(i ,new ArrayList<>());
    }

    for (int i = 0; i < stones.length; i++) {

      if (stones[i] != null && stones[i].getPlayer() != null){
        game.sendTo(game.getPlayer(stones[i].getPlayer().getId()).getUser(),
                new ChooseCardEvent(game.getGameID(), chosenCards, game.getGameID()));
        game.setCurrentPlayer(stones[i].getPlayer().getId());
        Move move = acquireMove();
        if (move instanceof ChooseCardMove) {
          ChooseCardMove chooseCard = (ChooseCardMove) move;
          int cardId = chooseCard.getCardId();
          chosenCards[i]= cardId;
          Card card = activeCards.get(cardId);
          market.removeCard(cardId);
          if (card instanceof LocationCard) {
            game.sendAll(((LocationCard) card).exec(game, stones[i].getPlayer().getId()));
          } else {
            stones[i].getPlayer().addCard(card);
            cardTypes.get(stones[i].getPlayer().getId()).add(card.getType());
          }
        }
      }
    }
    game.sendAll(new AllChosenCardsEvent(chosenCards, game.getGameID()));
    sendNewInventories(cardTypes );
  }

  public void sendNewInventories(ArrayList<ArrayList<CardType>> cardTypes){
    game.sendAll(new InventoryUpdateEvent(cardTypes, game.getGameID()));
  }

  private Move acquireMove() {
    game.getExecutor().waitForMove();
    if (game.getExecutor().getMove() != null) {
      return game.getExecutor().getMove();
    }
    return null;
  }
}