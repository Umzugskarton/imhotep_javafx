package game.board;

import java.util.ArrayList;

public class Temple extends Site
    implements StoneSite {

  private ArrayList<Stone> temple = new ArrayList<>();

  @Override
  public ArrayList<Stone> getStones() {
    return temple;
  }

  public Temple(int playerCount) {
    super(playerCount);
  }

  @Override
  public int[] getPoints() {

    int[] points = new int[5];
    int size = Math.min(temple.size(), 5);
    for (int i = 0; i < size; i++) {
      points[temple.get(temple.size() - i).getPlayer().getId()]++;
    }
    return points;
  }

  @Override
  public void addStones(ArrayList<Stone> stones) {
    temple.addAll(stones);
  }

  @Override
  public boolean dockShip(Ship ship) {
    if (this.getDockedShip() != null) {
      return false;
    }
    temple.addAll(ship.getStones());
    return true;
  }
}
