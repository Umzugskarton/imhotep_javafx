package game.board;

import java.util.ArrayList;

/**
 * Repräsentiert eine Pyramide.
 */
public class Pyramids extends StoneSite {

  private int[] positionValues = {2, 1, 3, 2, 4, 3, 2, 1, 3, 2, 3, 1, 3, 4};
  private final static int standardValue = 1;

  /**
   * Gibt Punktewerte für die verschiedenen Felder zurück.
   * @return Punktewerte für die ersten Felder
   */
  public int[] getPositionValues() {
    return positionValues;
  }

  /**
   * Punktewert für die Felder nach den definierten.
   * @return Standard-Punktewert
   */
  public int getStandardValue() {
    return standardValue;
  }

  /**
   * Erstellt eine neue Pyramide.
   * @param playerCount die Anzahl der Spieler im Spiel
   */
  public Pyramids(int playerCount) {
    super(playerCount);
  }

  @Override
  public int[] getPoints() {
    int[] points = new int[playerCount];
    int i = 0;
    for (Stone s : stoneSite) {
      if (i < positionValues.length) {
        points[s.getPlayer().getId()] += positionValues[i++];
      } else {
        points[s.getPlayer().getId()] += standardValue;
      }
    }
    return points;
  }

  @Override
  public Ship getDockedShip() {
    return super.getDockedShip();
  }
}
