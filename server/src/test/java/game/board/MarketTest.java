package game.board;

import static org.junit.jupiter.api.Assertions.assertEquals;

import game.board.cards.Card;
import game.board.cards.LocationCard;
import game.board.cards.OrnamentCard;
import game.board.cards.StatueCard;
import game.board.cards.ToolCard;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import requests.gamemoves.CardType;

public class MarketTest {

  @Test
  public void addCardsTest() {

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
