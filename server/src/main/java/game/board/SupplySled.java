package game.board;

public class SupplySled {

  private static final int CAPACITY = 5;
  private int stones = 0;

  public int getCapacity() {
    return CAPACITY;
  }

  public int getStones() {
    return stones;
  }

  /**
   * @param amount the number of stones to add
   */
  public void addStones(int amount) {
    stones = Math.min(stones + amount, CAPACITY);
  }

  public void addStones() {
    int additionalStones = 3;
    addStones(additionalStones);
  }

  public boolean removeStone() {
    if (this.stones > 0) {
      this.stones--;
      return true;
    }
    return false;
  }
}
