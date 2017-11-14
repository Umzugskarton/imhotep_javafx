package game.board;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import game.Player;
import java.util.ArrayList;
import org.junit.Test;
import org.mockito.Mock;

public class BurialChamberTest {

  @Mock
  Player p1;
  @Mock
  Player p3;
  @Mock
  Player p4;
  @Mock
  Player p2;

  @Test
  public void BurialChamberGetPoints() {
    BurialChamber test = new BurialChamber(4);
    Stone[] stones = new Stone[13];
    p1 = mock(Player.class);
    p2 = mock(Player.class);
    p3 = mock(Player.class);
    p4 = mock(Player.class);
    when(p1.getId()).thenReturn(0);
    when(p2.getId()).thenReturn(1);
    when(p3.getId()).thenReturn(2);
    when(p4.getId()).thenReturn(3);

    stones[0] = new Stone(p3);
    stones[1] =(new Stone(p3));
    stones[2]=(new Stone(p3));
    stones[3]=(new Stone(p4));
    stones[4]=(new Stone(p2));
    stones[5]=(new Stone(p3));
    stones[6]=(new Stone(p3));
    stones[7]=(new Stone(p3));
    stones[8]=(new Stone(p4));
    stones[9]=(new Stone(p4));
    stones[10]=(new Stone(p4));
    stones[11]=(new Stone(p4));
    stones[12]=(new Stone(p4));
    test.addStones(stones);
    int[] points = test.getPoints();
    assert points.length == 4;
    assert points[0] == 0;
    assert points[1] == 1;
    assert points[2] == 13;
    assert points[3] == 10;
  }
}
