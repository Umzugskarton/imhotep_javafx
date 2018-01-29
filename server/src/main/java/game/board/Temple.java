package game.board;

import java.util.ArrayList;

public class Temple extends StoneSite {

  public Temple(int playerCount) {
    super(playerCount);
  }

  @Override
  public int[] getPoints() {

    int[] points = new int[this.playerCount];
    int size = Math.min(stoneSite.size(), this.playerCount<3?4:5);
    for (int i = 0; i < size; i++) {
      points[stoneSite.get(stoneSite.size() - 1 - i).getPlayer().getId()]++;
    }
    return points;
  }

  @Override
  public Ship getDockedShip() {
    return super.getDockedShip();
  }
}
