package game.board;

import java.util.ArrayList;

public class Ship {
  private int size;
  private int minimumStones;
  private ArrayList<Player> stones;
  private boolean docked;

  public Ship(int size) {
    this.size = size;
    this.minimumStones = Math.max(size-1, 1);
  }

  public boolean isDocked() {
    return docked;
  }

  /**
   *
   * @return minimum number of stones needed on the ship to sail
   */
  public int getMinimumStones() {
    return minimumStones;
  }

  public ArrayList<Player> getStones() {
    return stones;
  }

  public boolean addStone(Player stone, int position) {
    if (stones.get(position) != null || stones.get(position) != Player.NOPLAYER) return false;
    stones.add(position, stone);
    return true;
  }
}
