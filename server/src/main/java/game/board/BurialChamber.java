package game.board;

import java.util.ArrayList;
import java.util.Arrays;

public class BurialChamber extends Site
    implements StoneSite {

  private ArrayList<Stone> burialChamber = new ArrayList<>();

  public BurialChamber(int playerCount) {
    super(playerCount);
  }

  // TODO
  //IDEE: Rekursives Aufrufen von getFieldSize() auf Nachfolgern
  //Summieren der Punkte verbesserungsbedürftig; wichtiger: Funktioniert das Prinzip?
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

  private void addPlayerStone(int sinceLast, ArrayList<Integer> stones) {
    if (sinceLast > 2) {
      stones.add(1);
    } else {
      stones.add(stones.remove(stones.size())); //is this real life
    }
  }

  @Override
  public ArrayList<Stone> getStones() {
    return burialChamber;
  }

  @Override
  public void addStones(Stone[] stones) {
    burialChamber.addAll(Arrays.asList(stones));
  }

  public boolean dockShip(Ship ship) {
    if (this.getDockedShip() != null) {
      return false;
    }
    addStones(ship.getStones());
    return true;
  }
}
