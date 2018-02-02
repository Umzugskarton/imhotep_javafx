package game.board;

/**
 * Versorgungsplättchen mit dem Steinvorrat eines Players.
 */
public class SupplySled {

  private static final int CAPACITY = 5;
  private static final int STANDARD_RESUPPLY = 3;
  private int stones = 0;

  /**
   * Aktuelle Anzahl der Steine auf dem Plättchen.
   *
   * @return Steinvorrat des besitzenden Spielers
   */
  public int getStones() {
    return stones;
  }

  /**
   * @param amount die Anzahl der hinzuzufügenden Steine
   */
  public void addStones(int amount) {
    stones = Math.min(stones + amount, CAPACITY);
  }

  /**
   * Fügt dem SupplySled die Standardanzahl neuer Steine hinzu.
   */
  public void addStones() {
    addStones(STANDARD_RESUPPLY);
  }

  /**
   * Entfernt einen Stein vom SupplySled.
   *
   * @return true, wenn noch mindestens ein Stein verfügbar war.
   */
  public boolean removeStone() {
    if (this.stones > 0) {
      this.stones--;
      return true;
    }
    return false;
  }
}
