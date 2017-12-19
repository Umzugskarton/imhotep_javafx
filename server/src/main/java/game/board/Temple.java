package game.board;

import java.util.ArrayList;
import java.util.Arrays;

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

    int[] points = new int[this.playerCount];
    int size = Math.min(temple.size(), this.playerCount<3?4:5);
    for (int i = 0; i < size; i++) {
      points[temple.get(temple.size() - 1 - i).getPlayer().getId()]++;
    }
    return points;
  }

  @Override
  public void addStones(Stone[] stones) {
    temple.addAll(Arrays.asList(stones));
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
