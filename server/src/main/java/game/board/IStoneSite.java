package game.board;

import java.util.ArrayList;

public interface IStoneSite {

  /**
   * Returns an array containing each player's points, with the first Player's points
   * at index 0, the second Player's points at 1 etc.
   *
   * @return an array containing each player's points for the IStoneSite
   */
  int[] getPoints();

  /**
   * @param stones the stones to add to the StoneSite
   */
  void addStones(Stone[] stones);

  /**
   * Gibt eine ArrayList aller vorhandenen Steine auf der StoneSite zurück.
   * @return all stones on the site
   */
  ArrayList<Stone> getStones();
}
