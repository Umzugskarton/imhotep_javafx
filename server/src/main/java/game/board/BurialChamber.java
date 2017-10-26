package game.board;

import java.util.ArrayList;

public class BurialChamber extends Site
                           implements StoneSite {

  private ArrayList<Player>[] burialChamber;

  // TODO
  @Override
  public int[] getPoints() {
    return new int[0];
  }

  // TODO
  @Override
  public ArrayList<Player> getStones() {
    return null;
  }

  @Override
  public void addStones(ArrayList<Player> stones) {

  }

  public boolean dockShip(Ship ship) {
    if (this.getDockedShip() != null) return false;
    addStones(ship.getStones());
    return true;
  }
}
