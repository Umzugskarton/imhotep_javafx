package game.board.cards;

import static org.junit.jupiter.api.Assertions.*;

import game.board.Cards.OrnamentCard;
import org.junit.jupiter.api.Test;

class OrnamentCardTest {

  @Test
  void calc() {
    OrnamentCard ocp = new OrnamentCard("pyramid");
    OrnamentCard oct = new OrnamentCard("temple");
    OrnamentCard ocb = new OrnamentCard("burialchamber");
    OrnamentCard oco = new OrnamentCard("obelisk");

    int[] arr = {13,0,4,21};
    int pp = ocp.calc(arr);
    assertEquals(4, pp);
    int pt = oct.calc(arr);
    assertEquals(0,pt);
    int pb = ocb.calc(arr);
    assertEquals(1,pb);
    int po = oco.calc(arr);
    assertEquals(7,po);
  }
}