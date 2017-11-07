package game.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class Obelisks extends Site
                      implements StoneSite {

  private ArrayList<Stone> obelisks;
  private int playerCount;

  public Obelisks(int playerCount) {
    this.playerCount = playerCount;
  }

  @Override
  public ArrayList<Stone> getStones() {
    return obelisks;
  }

  // TODO
  // Bei Gleichstand müssen die Punkte addiert und durch die Anzahl der beteiligten Spieler geteilt werden

  // Keinen Plan ob das so funktioniert,
  // müssen wir später testen
  @Override
  public int[] getPoints() {
    int[] points = new int[5];
    Integer[] stonesPerPlayer = new Integer[4];
    /*
    stonesPerPlayer[0] = Collections.frequency(obelisks, Player.PLAYER1);
    stonesPerPlayer[1] = Collections.frequency(obelisks, Player.PLAYER2);
    stonesPerPlayer[2] = Collections.frequency(obelisks, Player.PLAYER3);
    stonesPerPlayer[3] = Collections.frequency(obelisks, Player.PLAYER4);
    */
    Integer[] players = new Integer[4];
    players[0] = stonesPerPlayer[0];
    players[1] = stonesPerPlayer[1];
    players[2] = stonesPerPlayer[2];
    players[3] = stonesPerPlayer[3];
    Arrays.sort(stonesPerPlayer, Collections.reverseOrder());
    for (int i=0; i<4; i++){
      if (players[i] == 0){
        points[i+1] = 0;
        break;
      }
      switch(playerCount){
        case 2:
          if (players[i] == stonesPerPlayer[0]) points[i+1] = 10;
          if (players[i] == stonesPerPlayer[1]) points[i+1] = 1;
          break;
        case 3:
          if (players[i] == stonesPerPlayer[0]) points[i+1] = 12;
          if (players[i] == stonesPerPlayer[1]) points[i+1] = 6;
          if (players[i] == stonesPerPlayer[2]) points[i+1] = 1;
          break;
        case 4:
          if (players[i] == stonesPerPlayer[0]) points[i+1] = 15;
          if (players[i] == stonesPerPlayer[1]) points[i+1] = 10;
          if (players[i] == stonesPerPlayer[2]) points[i+1] = 5;
          if (players[i] == stonesPerPlayer[3]) points[i+1] = 1;
          break;
        default:
          break;
      }
    }
    return points;
    }


  @Override
  public void addStones(ArrayList<Stone> stones) {
    this.obelisks.addAll(stones);
  }

  @Override
  public boolean dockShip(Ship ship) {
    if (this.getDockedShip() != null) return false;
    addStones(ship.getStones());
    return true;
  }
}
