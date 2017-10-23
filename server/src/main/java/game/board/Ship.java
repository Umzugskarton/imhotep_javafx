package game.board;

import java.util.ArrayList;

public class Ship {
  private int minimumStones;
  private ArrayList<Player> stones;
  private boolean docked;

  public Ship(int size) {

  }

  public boolean isDocked() {
    return docked;
  }

  public int getMinimumStones() {
    return minimumStones;
  }

  public ArrayList<Player> getStones() {
    return stones;
  }
}
