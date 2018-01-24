package game.board;

import java.util.ArrayList;
import java.util.Arrays;

public class Pyramids extends Site
    implements StoneSite {

  private ArrayList<Stone> pyramid = new ArrayList<>();
  private int[] positionValues = {2, 1, 3, 2, 4, 3, 2, 1, 3, 2, 3, 1, 3, 4};
  private int standardValue = 1;
  private int currentTurnStones = 0;

  public int[] getPositionValues() {
    return positionValues;
  }

  public int getStandardValue() {
    return standardValue;
  }

  public Pyramids(int playerCount, int standardValue) {
    super(playerCount);
    this.standardValue = standardValue;
  }

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

  /**
   * Gibt die Punkte zurück, die im Verlauf der aktuellen Runde gemacht wurden.
   * Bereitet die Pyramide auf die nächste Runde vor.
   *
   * @return Punkte für die aktuelle Runde
   */
  public int[] getPointsAndFinishTurn() {
    int[] points = new int[playerCount];
    for (int i = pyramid.size() - currentTurnStones; i < pyramid.size(); i++) {
      if (i < positionValues.length) {
        points[pyramid.get(i).getPlayer().getId()] += positionValues[i++];
      } else {
        points[pyramid.get(i).getPlayer().getId()] += standardValue;
      }
    }
    currentTurnStones = 0;
    return points;
  }

  @Override
  public void addStones(Stone[] stones) {
    for (Stone stone : stones) {
      if (stone != null) {
        currentTurnStones++;
        pyramid.add(stone);
      }
    }
  }

  @Override
  public boolean dockShip(Ship ship) {
    if (this.getDockedShip() != null) {
      return false;
    }
    setDockedShip(ship);
    addStones(ship.getStones());
    return true;
  }

  @Override
  public boolean isDocked() {
    return this.getDockedShip() != null;
  }

  @Override
  public Ship getDockedShip() {
    return super.getDockedShip();
  }
}
