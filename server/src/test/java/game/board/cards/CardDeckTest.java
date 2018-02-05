package game.board.cards;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class CardDeckTest {

  @Test
  public void createCardTest() {
    CardDeck cardDeck = new CardDeck();
    assertEquals(24, cardDeck.getDeck().size());
  }
}
