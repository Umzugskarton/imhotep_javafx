package game.board;


public class Ship {
  private int id;
  private int size;
  private int minimumStones;
  private Stone[] stones;
  private boolean docked;

  /**
   * Erstellt ein neues Schiff.
   * @param id
   * @param size Anzahl der Steinplätze auf dem Schiff
   */
  public Ship(int id, int size) {
    this.id = id;
    this.size = size;
    this.docked = false;
    this.minimumStones = Math.max(size-1, 1);
    stones = new Stone[size];
  }

  public int getId() {
    return id;
  }

  public boolean isDocked() {
    return docked;
  }

  /**
   * Anzahl der Steine, ab denen das Schiff absegeln darf.
   * @return Mindestanzahl der Steine auf dem Schiff, damit es absegeln darf.
   */
  public int getMinimumStones() {
    return minimumStones;
  }

  public void setDocked(boolean docked) {
    this.docked = docked;
  }

  public Stone[] getStones() {
    return stones;
  }

  /**
   * Fügt dem Schiff einen Stein hinzu.
   * @param stone der Stein, der auf das Schiff gesetzt wird.
   * @param position die Position auf dem Schiff, auf die der Stein gesetzt wird.
   * @return Erfolg
   */
  public boolean addStone(Stone stone, int position) {
    if (docked || (position > size || (stones.length > position && stones[position] != null))) return false;
    stones[position] =  stone;
    return true;
  }

  public Stone[] sortStones(int[] sortedStones) {
    for(int i = 0; i < stones.length; i++) {
      Stone temp;
      temp = stones[i];
      stones[i] = stones[sortedStones[i]];
      stones[sortedStones[i]] = temp;
    }
      return stones;
  }

}
