package game.board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import game.Player;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class TempleTest {

  @Test
  void getPoints() {
    Temple test = new Temple(4);
    ArrayList<Stone> stones = new ArrayList<>();
    Player p1 = mock(Player.class);
    Player p2 = mock(Player.class);
    Player p3 = mock(Player.class);
    Player p4 = mock(Player.class);
    when(p1.getId()).thenReturn(0);
    when(p2.getId()).thenReturn(1);
    when(p3.getId()).thenReturn(2);
    when(p4.getId()).thenReturn(3);

    Stone[] st = new Stone[6];
    st[0] = new Stone(p3);
    st[1] = new Stone(p3);
    st[2] = new Stone(p3);
    st[3] = new Stone(p4);
    st[4] = new Stone(p2);
    st[5] = new Stone(p3);
    test.addStones(st);
    int[] points = test.getPoints();
    assertEquals(4, points.length);
    assertEquals(0,points[0]);
    assertEquals(1, points[1]);
    assertEquals(3, points[2]);
    assertEquals(1, points[3]);
  }
}