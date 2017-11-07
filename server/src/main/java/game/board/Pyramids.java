package game.board;

import java.util.ArrayList;

public class Pyramids extends Site
                      implements StoneSite {
  private ArrayList<Stone> pyramid;
  private final int players;
  private int[] positionValues = {2,1,3,2,4,3,2,1,3,2,3,1,3,4};
  private int standardValue = 1;

  public int[] getPositionValues() {
    return positionValues;
  }

  public int getStandardValue() {
    return standardValue;
  }

  public Pyramids(int players, int standardValue) {
    this.players = players;
    this.standardValue = standardValue;
  }

  @Override
  public ArrayList<Stone> getStones() {
    return pyramid;
  }

  @Override
  public int[] getPoints() {
    int[] points = new int[players];
    int i = 0;
    for (Stone s : pyramid) {
      if (i < positionValues.length) {
        points[s.getPlayer().getPlayerId()] += positionValues[i++];
      } else {
        points[s.getPlayer().getPlayerId()] += standardValue;
      }
    }
    return points;
  }

  @Override
  public void addStones(ArrayList<Stone> stones) {
    this.pyramid.addAll(stones);
  }

  @Override
  public boolean dockShip(Ship ship) {
    if (this.getDockedShip() != null) return false;
    addStones(ship.getStones());
    return true;
  }
}
