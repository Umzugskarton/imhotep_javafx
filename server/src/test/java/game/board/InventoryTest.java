package game.board;

import requests.gamemoves.CardType;
import game.Inventory;
import game.board.cards.OrnamentCard;
import game.board.cards.StatueCard;
import game.board.cards.ToolCard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InventoryTest {

  @Test
  void ownsCardTest() {
    Inventory inventory = new Inventory();

    CardType cardO = CardType.TEMPLE;
    CardType cardT = CardType.LEVER;

    OrnamentCard card1 = new OrnamentCard(cardO);
    ToolCard card2 = new ToolCard(cardT);
    StatueCard card3 = new StatueCard();

    //Expected
    boolean test1 = true;
    boolean test2 = true;
    boolean test3 = true;

    inventory.addCard(card1);
    inventory.addCard(card2);
    inventory.addCard(card3);

    //Ergebnis
    boolean result1 = inventory.ownsCard(card1);
    boolean result2 = inventory.ownsCard(card2);
    boolean result3 = inventory.ownsCard(card3);

    assertEquals(test1, result1);
    assertEquals(test2, result2);
    assertEquals(test3, result3);


  }
}
