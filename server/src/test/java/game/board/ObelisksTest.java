package game.board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import game.Player;
import org.junit.Test;
import org.mockito.Mock;

public class ObelisksTest {

  @Mock
  Player p1;
  @Mock
  Player p3;
  @Mock
  Player p4;
  @Mock
  Player p2;

  @Test
  public void ObelisksGetPoints() {
    Obelisks test = new Obelisks(4);
    Stone[] stones = new Stone[3];
    p1 = mock(Player.class);
    p2 = mock(Player.class);
    p3 = mock(Player.class);
    p4 = mock(Player.class);
    when(p1.getId()).thenReturn(0);
    when(p2.getId()).thenReturn(1);
    when(p3.getId()).thenReturn(2);
    when(p4.getId()).thenReturn(3);
    stones[0] = new Stone(p3);
    stones[1] = new Stone(p4);
    stones[2] = new Stone(p2);
    test.addStones(stones);
    int[] points = test.getPoints();
    assertEquals(4, points.length);
  }
}
