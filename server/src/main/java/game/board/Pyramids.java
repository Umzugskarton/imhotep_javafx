package game.board;

import java.util.ArrayList;

public class Pyramids extends Site
                      implements StoneSite {
  private ArrayList<Player> pyramid;
  private int[] positionValues = {2,1,3,2,4,3,2,1,3,2,3,1,3,4};
  private int standardValue = 1;

  public int[] getPositionValues() {
    return positionValues;
  }

  public int getStandardValue() {
    return standardValue;
  }

  public Pyramids(int standardValue) {
    this.standardValue = standardValue;
  }

  @Override
  public ArrayList<Player> getStones() {
    return pyramid;
  }

  @Override
  public int[] getPoints() {
    int[] points = new int[5];
    int i = 0;
    for (Player p : pyramid) {
      if (i < positionValues.length) {
        points[p.ordinal()] += positionValues[p.ordinal()];
      } else {
        points[p.ordinal()] += standardValue;
      }
      i++;
    }
    return points;
  }

  @Override
  public void addStones(ArrayList<Player> stones) {
    this.pyramid.addAll(stones);
  }

  @Override
  public boolean dockShip(Ship ship) {
    if (this.getDockedShip() != null) return false;
    addStones(ship.getStones());
    return true;
  }
}
