package game.board;

import game.Player;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ShipStoneSortTest {

  //WunschSortierung der Steine
  int sortedstones[] = {1, 2, 0, 3};

  @Mock
  Player p1;
  @Mock
  Player p2;


  @Test
  public void VTSManualDump() {
    Ship test = new Ship(1);
    while (test.getSize() != 4) {
      test = new Ship(1);
    }
    Stone[] stones = new Stone[4];
    int[] newOrder = new int[4];
    int[] testOrder = {0, 1, 0, 1};

    p1 = mock(Player.class);
    p2 = mock(Player.class);
    when(p1.getId()).thenReturn(0);
    when(p2.getId()).thenReturn(1);
    stones[0] = new Stone(p1);
    stones[1] = new Stone(p1);
    stones[2] = new Stone(p2);
    stones[3] = new Stone(p2);
    test.addStone(stones[0], 0);
    test.addStone(stones[1], 1);
    test.addStone(stones[2], 2);
    test.addStone(stones[3], 3);

    //Sortierung der Steine auf dem Schiff mit selbstgewähltem Array
    test.sortStones(sortedstones);
    Stone[] newStones = test.getStones();
    //Player-ID der Steine wird in newOrder gelegt
    for (int i = 0; i < newStones.length; i++) {
      newOrder[i] = newStones[i].getPlayer().getId();
    }
    assertArrayEquals(testOrder, newOrder);
  }

}
