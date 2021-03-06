package game.board;

import java.util.concurrent.ThreadLocalRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Ship {

  private final Logger log = LoggerFactory.getLogger(getClass().getName());
  private static final int MAX_SLOTS = 4;
  private static final int MIN_SLOTS = 2;

  private final int id;
  private final int size;
  private final int minimumStones;
  private final Stone[] stones;
  private boolean docked;

  /**
   * Erstellt ein neues Schiff.
   *
   * @param id Nummer des Schiffs
   * @param size Anzahl der Steinplätze auf dem Schiff
   */
  private Ship(int id, int size) {
    this.id = id;
    this.size = size;
    this.docked = false;
    this.minimumStones = Math.max(size - 1, 1);
    stones = new Stone[size];
  }

  /**
   * Erstellt ein neues Schiff mit einer zufälligen Größe.
   *
   * @param id Nummer des Schiffs
   */
  public Ship(int id) {
    this(id, getRandomSize());
  }

  private static int getRandomSize() {
    //zweiter Parameter muss MAX_SLOTS+1 sein, weil die Rückgabe streng kleiner ist
    return ThreadLocalRandom.current().nextInt(MIN_SLOTS, MAX_SLOTS + 1);
  }

  public int getId() {
    return id;
  }

  public boolean isDocked() {
    return docked;
  }

  /**
   * Anzahl der Steine, ab denen das Schiff absegeln darf.
   *
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

  public int getSize() {
    return this.size;
  }

  /**
   * Fügt dem Schiff einen Stein hinzu.
   *
   * @param stone der Stein, der auf das Schiff gesetzt wird.
   * @param position die Position auf dem Schiff, auf die der Stein gesetzt wird.
   * @return Erfolg
   */
  public boolean addStone(Stone stone, int position) {
    if (docked || (position >= size || (stones.length > position && stones[position] != null))) {
      log.warn("not adding stone to ship. id:{} position:{} size:{}", id, position, size);
      return false;
    }
    stones[position] = stone;
    log.warn("PUT ON SHIP {} STONE ID {}", id, stones[position].getPlayer().getId());
    return true;
  }

  public int getLoadedStones() {
    int stoneCount = 0;
    for (Stone stone : stones) {
      if (stone != null) {
        stoneCount++;
      }
    }
    return stoneCount;
  }

  public Stone[] sortStones(int[] sortedStones) {
    Stone[] tempStones = new Stone[stones.length];
    for (int i = 0; i < stones.length; i++) {
      if (sortedStones[i] != -1) {
        tempStones[i] = stones[sortedStones[i]];
      }
    }
    System.arraycopy(tempStones, 0 , stones, 0 , tempStones.length );
    return stones;
  }



  public int[] getCargoAsIntArrayByShip() {
    int[] shipInt = new int[stones.length];
    for (int i = 0; i < stones.length; i++) {
      if (stones[i] != null) {
        shipInt[i] = stones[i].getPlayer().getId();
      } else {
        shipInt[i] = -1;
      }
    }
    return shipInt;
  }
}
