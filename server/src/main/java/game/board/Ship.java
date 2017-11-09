package game.board;

import java.util.ArrayList;

public class Ship {

  private int size;
  private int minimumStones;
  private ArrayList<Stone> stones;
  private boolean docked;

  public Ship(int size) {
    this.size = size;
    this.minimumStones = Math.max(size - 1, 1);
  }

  public boolean isDocked() {
    return docked;
  }

  /**
   * @return minimum number of stones needed on the ship to sail
   */
  public int getMinimumStones() {
    return minimumStones;
  }

  public ArrayList<Stone> getStones() {
    return stones;
  }

  public boolean addStone(Stone stone, int position) {
    if (position > size || (stones.size() > position && stones.get(position) != null)) {
      return false;
    }
    stones.add(position, stone);
    return true;
  }
}
