package game.board;

import game.board.cards.*;
import org.junit.Test;
import requests.gamemoves.CardType;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MarketTest {

    @Test
    public void addCardsTest() {
        LocationCard card1 = new LocationCard(CardType.PAVEDPATH);
        OrnamentCard card2 = new OrnamentCard(CardType.PYRAMID);
        StatueCard card3 = new StatueCard();
        ToolCard card4 = new ToolCard(CardType.HAMMER);
        List<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        Market test = new Market(2, cards);
    }

    @Test
    public void newRoundTest() {

    }

    @Test
    public void dockShipTest() {
        Market test = new Market(2, new ArrayList<>());
        Ship testS = new Ship(2);
        assertEquals(true, test.dockShip(testS));
        assertEquals(false, test.dockShip(testS));
    }
}
