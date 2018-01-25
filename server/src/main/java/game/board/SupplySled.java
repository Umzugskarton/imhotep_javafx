package game.board;

public class SupplySled {

  private int capacity = 5;
  private int stones = 0;

  public SupplySled(int capacity) {
    this.capacity = capacity;
  }

  public int getCapacity() {
    return capacity;
  }

  public int getStones() {
    return stones;
  }

  /**
   * @param amount the number of stones to add
   */
  public void addStones(int amount) {
    stones = Math.max(stones + amount, capacity);
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
