package game.board;

public class Ship {
  private int size;
  private int minimumStones;
  private Stone[] stones;
  private boolean docked;

  public Ship(int size) {
    this.size = size;
    stones = new Stone[size];
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

  public Stone[] getStones() {
    return stones;
  }

  public boolean addStone(Stone stone, int position) {
    if (position > size || (stones.length > position && stones[position] != null)) return false;
    stones[position] =  stone;
    return true;
  }
}
