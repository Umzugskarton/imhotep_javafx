package game.board;

import java.util.ArrayList;

public interface StoneSite {

  /**
   * Returns an array containing each player's points. Note that PLAYER1's points are at index 1,
   * not at index 0. If applicable, index 0 contains the amount of points NOPLAYER gets. Otherwise,
   * it contains a 0.
   * @return an array containing each player's points for the StoneSite
   */
  int[] getPoints();

  /**
   *
   * @param stones the stones to add to the StoneSite
   */
  void addStones (ArrayList<Player> stones);

  ArrayList<Player> getStones();
}
