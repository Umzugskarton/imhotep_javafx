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
import requests.gamemoves.Move;
import requests.gamemoves.VoyageToMarketMove;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        if (!ship.isDocked()) {
            int loadedStones = 0;
            for (Stone stone : ship.getStones()) {
                if (stone != null) {
                    loadedStones++;
                }
            }
            Arrays.asList(ship.getStones()).size();

            if (loadedStones >= ship.getMinimumStones()) {
                if (market.dockShip(ship)) {
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
                                game.getPyramids().addStone(newStone);
                                LocationCardEvent e = new LocationCardEvent(0);
                            } else if (card.getType() == CardType.SARCOPHAGUS) {
                                game.getBurialChamber().addStone(newStone);
                                LocationCardEvent e = new LocationCardEvent(1);
                            } else if (card.getType() == CardType.PAVEDPATH) {
                                game.getObelisks().addStone(newStone);
                                LocationCardEvent e = new LocationCardEvent(2);
                            }
                        } else {
                            stone.getPlayer().getInventory().addCard(card);
                        }
                        marketStones.add(stone.getPlayer().getId());
                    }
                    return new ShipDockedEvent(move.getShipId(), SiteType.MARKET, marketStones);
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

    private Card playerCardSelect(int playerId) {
        List<Card> activeCards = game.getMarket().getActiveCards();
        //TODO: Player-ID sucht sich eine Karte aus
        int choosenCard = 0;
        ChooseCardEvent chooseCardEvent = new ChooseCardEvent(playerId, choosenCard);
        game.sendAll(chooseCardEvent);
        return activeCards.get(choosenCard);
    }

}


