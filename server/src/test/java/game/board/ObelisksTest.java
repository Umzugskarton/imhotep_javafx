package game.board;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import game.Player;
import java.util.ArrayList;
import java.util.Arrays;
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
    ArrayList<Stone> stones = new ArrayList<>();
    p1 = mock(Player.class);
    p2 = mock(Player.class);
    p3 = mock(Player.class);
    p4 = mock(Player.class);
    when(p1.getId()).thenReturn(0);
    when(p2.getId()).thenReturn(1);
    when(p3.getId()).thenReturn(2);
    when(p4.getId()).thenReturn(3);
    stones.add(new Stone(p3));
    stones.add(new Stone(p4));
    stones.add(new Stone(p2));
    test.addStones(stones);
    int[] points = test.getPoints();
    assert points.length == 4;
    System.out.println(Arrays.toString(points));
  }
}
