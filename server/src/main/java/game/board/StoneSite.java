package game.board;

import java.util.ArrayList;

public interface StoneSite {

  /**
   * Returns an array containing each player's points, with the first Player's points
   * at index 0, the second Player's points at 1 etc.
   * @return an array containing each player's points for the StoneSite
   */
  int[] getPoints();

  /**
   *
   * @param stones the stones to add to the StoneSite
   */
  void addStones (ArrayList<Stone> stones);

  void dockShip();

  ArrayList<Stone> getStones();
}
