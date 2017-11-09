package game.board;

import java.util.ArrayList;

public class BurialChamber extends Site
    implements StoneSite {

  private ArrayList<Stone> burialChamber = new ArrayList<>();

  public BurialChamber(int playerCount) {
    super(playerCount);
  }

  @Override
  public int[] getPoints() {
    boolean[] checked = new boolean[burialChamber.size()];
    int[] points = new int[4];
    for (int i = 0; i < burialChamber.size(); i++) {
      int playerId = burialChamber.get(i).getPlayer().getId();
      int size = getFieldSize(i, playerId, checked);
      if (size == 1) {
        points[playerId] += 1;
      } else if (size == 2) {
        points[playerId] += 3;
      } else if (size == 3) {
        points[playerId] += 6;
      } else if (size == 4) {
        points[playerId] += 10;
      } else if (size >= 5) {
        points[playerId] += 15 + (size - 5) * 2;
      }
    }
    return points;
  }

  private int getFieldSize(int position, int playerId, boolean[] checked) {
    if (position >= burialChamber.size()
        || burialChamber.get(position).getPlayer().getId() != playerId
        || checked[position]) {
      return 0;
    }
    checked[position] = true;
    int number = 1;
    if (position % 3 == 0 || position % 3 == 1) {
      number += getFieldSize(position + 1, playerId, checked);
    }
    number += getFieldSize(position + 3, playerId, checked);
    return number;
  }

  @Override
  public ArrayList<Stone> getStones() {
    return burialChamber;
  }

  @Override
  public void addStones(ArrayList<Stone> stones) {

  }

  public boolean dockShip(Ship ship) {
    if (this.getDockedShip() != null) {
      return false;
    }
    addStones(ship.getStones());
    return true;
  }
}
