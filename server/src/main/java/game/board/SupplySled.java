package game.board;

public class SupplySled {
  private int size = 5;
  private int stones;

  public SupplySled(int size) {

  }

  public int getSize() {
    return size;
  }

  public int getStones() {
    return stones;
  }

  public boolean addStones(int amount) {
    if (amount+stones > size) return false;
    stones += amount;
    return true;
  }
}
