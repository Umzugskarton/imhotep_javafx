package game.board.cards;

import game.board.Cards.OrnamentCard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created on 27.01.2018.
 */
public class OrnamentCardTest {

  @Test
  public void calcTest() {
    OrnamentCard ornamentCard = new OrnamentCard("pyramid");
    OrnamentCard ornamentCard2 = new OrnamentCard("temple");
    OrnamentCard ornamentCard3 = new OrnamentCard("burialchamber");
    OrnamentCard ornamentCard4 = new OrnamentCard("obelisk");

    int[] stones = {3, 3, 3, 3};

    int newValue = ornamentCard.calc(stones);
    int newValue2 = ornamentCard2.calc(stones);
    int newValue3 = ornamentCard3.calc(stones);
    int newValue4 = ornamentCard4.calc(stones);

    int testValue = 1;

    assertEquals(testValue, newValue);
    assertEquals(testValue, newValue2);
    assertEquals(testValue, newValue3);
    assertEquals(testValue, newValue4);


  }
}
