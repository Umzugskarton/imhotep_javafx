package game.board;

import java.util.ArrayList;
import java.util.Arrays;

public class Pyramids extends Site
    implements StoneSite {

  private ArrayList<Stone> pyramid = new ArrayList<>();
  private int[] positionValues = {2, 1, 3, 2, 4, 3, 2, 1, 3, 2, 3, 1, 3, 4};
  private int standardValue = 1;

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

  @Override
  public void addStones(Stone[] stones) {
      pyramid.addAll(Arrays.asList(stones));
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
