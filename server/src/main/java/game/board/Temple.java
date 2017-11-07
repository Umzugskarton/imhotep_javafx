package game.board;

import static java.lang.Math.min;

import java.util.ArrayList;

public class Temple extends Site
                    implements StoneSite {

  private ArrayList<Stone> temple;

  @Override
  public ArrayList<Stone> getStones() {
    return temple;
  }

  @Override
  public int[] getPoints() {
    int[] points = new int[5];
    int size = min(temple.size(), 5);
    for (int i = 0; i < size; i++) {
      points[temple.get(temple.size()-i).getPlayer().getPlayerId()]++;
    }
    return points;
  }

  @Override
  public void addStones(ArrayList<Stone> stones) {
    temple.addAll(stones);
  }

  @Override
  boolean dockShip(Ship ship) {
    if (this.getDockedShip() != null) return false;
    temple.addAll(ship.getStones());
    return true;
  }
}
