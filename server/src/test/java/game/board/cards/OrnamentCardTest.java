package game.board.cards;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import requests.gamemoves.CardType;

class OrnamentCardTest {

  @Test
  void calc() {
    OrnamentCard ocp = new OrnamentCard(CardType.PYRAMID);
    OrnamentCard oct = new OrnamentCard(CardType.TEMPLE);
    OrnamentCard ocb = new OrnamentCard(CardType.BURIALCHAMBER);
    OrnamentCard oco = new OrnamentCard(CardType.OBELISK);

    Integer[] arr = {13, 0, 4, 21};
    int pp = ocp.calc(arr);
    assertEquals(4, pp);
    int pt = oct.calc(arr);
    assertEquals(0, pt);
    int pb = ocb.calc(arr);
    assertEquals(1, pb);
    int po = oco.calc(arr);
    assertEquals(7, po);
  }
}