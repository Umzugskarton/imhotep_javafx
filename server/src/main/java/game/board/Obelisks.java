package game.board;

import java.util.ArrayList;
import java.util.Arrays;

public class Obelisks extends Site
                      implements StoneSite {

  private ArrayList<Player> obelisks;

  public Obelisks(int playerCount) {

  }

  @Override
  public ArrayList<Player> getStones() {
    return obelisks;
  }

  // TODO
  // ich *glaube* das macht was es soll.
  // Ich hoffe, dass der Code Müll ist und jemand
  // ne ordentliche Lösung findet.
  @Override
  public int[] getPoints() {
    int[] points = new int[5];
    int[] players = new int[4];
    for (Player p : obelisks) {
      if (p==Player.PLAYER1) players[0]++;
      else if (p==Player.PLAYER2) players[1]++;
      else if (p==Player.PLAYER3) players[2]++;
      else if (p==Player.PLAYER4) players[3]++;
    }
    int[] temp = new int[4];
    temp[0] = players[0];
    temp[1] = players[1];
    temp[2] = players[2];
    temp[3] = players[3];
    Arrays.sort(temp);
    for (int i : players) {
      if (i==temp[0]) points[i+1]=15;
      else if (i==temp[1]) points[i+1]=10;
      else if (i==temp[2]) points[i+1]=5;
      else if (i==temp[3]) points[i+1]=1;
    }
    return points;
  }

  @Override
  public void addStones(ArrayList<Player> stones) {
    this.obelisks.addAll(stones);
  }

  @Override
  public boolean dockShip(Ship ship) {
    if (this.getDockedShip() != null) return false;
    addStones(ship.getStones());
    return true;
  }
}
