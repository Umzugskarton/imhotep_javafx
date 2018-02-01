package game.board.cards;

import static org.junit.jupiter.api.Assertions.*;

import requests.GameMoves.CardType.Type;
import game.board.Cards.OrnamentCard;
import org.junit.jupiter.api.Test;

class OrnamentCardTest {

  @Test
  void calc() {
    OrnamentCard ocp = new OrnamentCard(Type.PYRAMID);
    OrnamentCard oct = new OrnamentCard(Type.TEMPLE);
    OrnamentCard ocb = new OrnamentCard(Type.BURIALCHAMBER);
    OrnamentCard oco = new OrnamentCard(Type.OBELISK);

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