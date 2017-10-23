package game.board;

import java.util.ArrayList;

public interface StoneSite {
  int[] getPoints();
  void addStones (ArrayList<Player> stones);
  ArrayList<Player> getStones();
}
