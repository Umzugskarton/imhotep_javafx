package game.board;

import java.util.ArrayList;

public class Pyramids extends Site
                      implements StoneSite {
  private ArrayList<Player> pyramid;
  private int[] positionValues;
  private int standardValue;

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

  // TODO
  @Override
  public int[] getPoints() {
    return new int[0];
  }

  // TODO
  @Override
  public void addStones(ArrayList<Player> stones) {

  }
}
