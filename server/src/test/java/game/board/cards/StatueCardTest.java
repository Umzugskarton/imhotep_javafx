package game.board.cards;

import game.board.Cards.StatueCard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created on 27.01.2018.
 */
public class StatueCardTest {

  @Test
  public void calcTest() {
    StatueCard statueCard = new StatueCard();
    int numberOfCards = 3;
    int testValue = 6;

    //TestBerechnung
    int newValue = statueCard.calc(numberOfCards);

    assertEquals(testValue, newValue);

  }
}
