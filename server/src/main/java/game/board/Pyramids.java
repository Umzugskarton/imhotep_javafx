package game.board;

import java.util.ArrayList;

/**
 * Repräsentiert eine Pyramide.
 */
public class Pyramids extends StoneSite {

  private ArrayList<Stone> pyramid = new ArrayList<>();
  private int[] positionValues = {2, 1, 3, 2, 4, 3, 2, 1, 3, 2, 3, 1, 3, 4};
  private int standardValue = 1;

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
   * @param standardValue Punktewert für die Felder auf der Pyramide, die nicht explizit
   * definiert sind.
   */
  public Pyramids(int playerCount, int standardValue) {
    super(playerCount);
    this.standardValue = standardValue;
  }

  /**
   * Erstellt eine neue Pyramide.
   * @param playerCount die Anzahl der Spieler im Spiel
   */
  public Pyramids(int playerCount) {
    super(playerCount);
  }

  @Override
  public ArrayList<Stone> getStones() {
    return pyramid;
  }

  @Override
  public int[] getPoints() {
    int[] points = new int[playerCount];
    int i = 0;
    for (Stone s : pyramid) {
      if (i < positionValues.length) {
        points[s.getPlayer().getId()] += positionValues[i++];
      } else {
        points[s.getPlayer().getId()] += standardValue;
      }
    }
    return points;
  }

  @Override
  public void addStones(Stone[] stones) {
    for (Stone stone : stones){
      if (stone !=null){
        pyramid.add(stone);
      }
    }
  }

  @Override
  public boolean dockShip(Ship ship) {
    if (this.getDockedShip() != null) {
      return false;
    }
    addStones(ship.getStones());
    return true;
  }
  @Override
  public boolean isDocked(){
    return this.getDockedShip() != null;
  }

  @Override
  public Ship getDockedShip() {
    return super.getDockedShip();
  }
}
