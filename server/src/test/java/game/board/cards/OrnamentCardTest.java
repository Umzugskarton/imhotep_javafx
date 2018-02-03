package game.board.cards;

import org.junit.jupiter.api.Test;
import requests.gamemoves.CardType;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrnamentCardTest {

  @Test
  void calc() {
    OrnamentCard ocp = new OrnamentCard(CardType.PYRAMID);
    OrnamentCard oct = new OrnamentCard(CardType.TEMPLE);
    OrnamentCard ocb = new OrnamentCard(CardType.BURIALCHAMBER);
    OrnamentCard oco = new OrnamentCard(CardType.OBELISK);

    int[] arr = {13, 0, 4, 21};
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