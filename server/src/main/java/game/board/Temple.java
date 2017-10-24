package game.board;

import static java.lang.Math.min;

import java.util.ArrayList;

public class Temple extends Site
                    implements StoneSite {

  private ArrayList<Player> temple;

  @Override
  public ArrayList<Player> getStones() {
    return temple;
  }

  @Override
  public int[] getPoints() {
    int[] points = new int[5];
    int size = min(temple.size(), 5);
    for (int i = 0; i < size; i++) {
      points[temple.get(temple.size()-i).ordinal()]++;
    }
    return points;
  }

  @Override
  public void addStones(ArrayList<Player> stones) {
    temple.addAll(stones);
  }
}
