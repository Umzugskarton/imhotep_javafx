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
   * @param amount die Anzahl der hinzuzufügenden Steine
   * @return false, wenn nicht genügend Platz für die gegebene Menge Steine ist.
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
