package game.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class Obelisks extends Site
    implements StoneSite {

  private ArrayList<Stone> obelisks = new ArrayList<>();

  public Obelisks(int playerCount) {
    super(playerCount);
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
    int[] points = new int[this.playerCount];
    Integer[] stonesPerPlayer = new Integer[this.playerCount];
    for(Stone stone : obelisks){
      stonesPerPlayer[stone.getPlayer().getId()]++;
    }
    //nur shallow copy, sollte aber funktionieren da nur die Position wichtig ist, Inhalt bleibt
    Integer[] players = stonesPerPlayer.clone();
    Arrays.sort(stonesPerPlayer, Collections.reverseOrder());
    for (int i = 0; i < players.length; i++) {
      if (players[i] == 0) {
        points[i] = 0;
        break;
      }
      switch (playerCount) {
        case 2:
          if (players[i] == stonesPerPlayer[0]) {
            points[i] = 10;
          }
          if (players[i] == stonesPerPlayer[1]) {
            points[i] = 1;
          }
          break;
        case 3:
          if (players[i] == stonesPerPlayer[0]) {
            points[i] = 12;
          }
          if (players[i] == stonesPerPlayer[1]) {
            points[i] = 6;
          }
          if (players[i] == stonesPerPlayer[2]) {
            points[i] = 1;
          }
          break;
        case 4:
          if (players[i] == stonesPerPlayer[0]) {
            points[i] = 15;
          }
          if (players[i] == stonesPerPlayer[1]) {
            points[i] = 10;
          }
          if (players[i] == stonesPerPlayer[2]) {
            points[i] = 5;
          }
          if (players[i] == stonesPerPlayer[3]) {
            points[i] = 1;
          }
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
    if (this.getDockedShip() != null) {
      return false;
    }
    addStones(ship.getStones());
    return true;
  }
}
