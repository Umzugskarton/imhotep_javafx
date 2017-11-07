package game.board;

import java.util.ArrayList;

public class BurialChamber extends Site
                           implements StoneSite {

  private ArrayList<Stone> burialChamber;

  // TODO
  @Override
  public int[] getPoints() {
    return new int[0];
  }

  @Override
  public ArrayList<Stone> getStones() {
    return burialChamber;
  }

  @Override
  public void addStones(ArrayList<Stone> stones) {

  }

  public boolean dockShip(Ship ship) {
    if (this.getDockedShip() != null) return false;
    addStones(ship.getStones());
    return true;
  }
}
