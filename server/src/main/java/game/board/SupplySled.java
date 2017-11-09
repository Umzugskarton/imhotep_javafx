package game.board;

public class SupplySled {

  private int size = 5;
  private int stones = 0;

  public SupplySled(int size) {
    this.size = size;
  }

  public int getSize() {
    return size;
  }

  public int getStones() {
    return stones;
  }

  /**
   * @param amount the number of stones to add
   * @return true if the resulting amount does not exceed size. Otherwise false
   */
  public boolean addStones(int amount) {
    if (amount + stones > size) {
      stones = size;
      return false;
    }
    stones += amount;
    return true;
  }
}
