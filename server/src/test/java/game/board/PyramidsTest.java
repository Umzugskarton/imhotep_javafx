package game.board;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import game.Player;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class PyramidsTest {

  @Test
  void getPoints() {
    Pyramids py = new Pyramids(4);
    ArrayList<Stone> stones = new ArrayList<>();
    Player p1 = mock(Player.class);
    Player p2 = mock(Player.class);
    Player p3 = mock(Player.class);
    Player p4 = mock(Player.class);
    when(p1.getId()).thenReturn(0);
    when(p2.getId()).thenReturn(1);
    when(p3.getId()).thenReturn(2);
    when(p4.getId()).thenReturn(3);

    stones.add(new Stone(p3));
    stones.add(new Stone(p3));
    stones.add(new Stone(p3));
    stones.add(new Stone(p4));
    stones.add(new Stone(p2));
    stones.add(new Stone(p3));
    stones.add(new Stone(p3));
    stones.add(new Stone(p3));
    stones.add(new Stone(p4));
    stones.add(new Stone(p4));
    stones.add(new Stone(p4));
    stones.add(new Stone(p4));
    stones.add(new Stone(p4));
    stones.add(new Stone(p4));
    stones.add(new Stone(p4));
    py.addStones(stones);
    int[] points = py.getPoints();
    assert points.length == 4;
    assert points[0] == 0;
    assert points[1] == 4;
    assert points[2] == 12;
    assert points[3] == 19;
  }

}