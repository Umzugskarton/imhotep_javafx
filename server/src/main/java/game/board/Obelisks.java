package game.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;


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
    int[] points = new int[playerCount];
    int[] pointsPerRank = new int[playerCount];
    switch(playerCount){
      case 2:
        pointsPerRank[0] = 10;
        pointsPerRank[1] = 1;
        break;
      case 3:
        pointsPerRank[0] = 12;
        pointsPerRank[1] = 6;
        pointsPerRank[2] = 1;
        break;
      case 4:
        pointsPerRank[0] = 15;
        pointsPerRank[1] = 10;
        pointsPerRank[2] = 5;
        pointsPerRank[3] = 1;
        break;
      default:
        break;
    }
    int[] stonesPerPlayer = new int[playerCount];
    for(Stone stone : obelisks){
      stonesPerPlayer[stone.getPlayer().getId()]++;
    }
    ObeliskHelper[] playerRank = new ObeliskHelper[playerCount];
    for(int i = 0; i < playerCount; i++){
      playerRank[i] = new ObeliskHelper(i, stonesPerPlayer[i]);
    }
    Arrays.sort(playerRank, Comparator.comparing(ObeliskHelper::getStones).reversed());
    for(int i = 0; i < playerCount; i++){
      if(playerRank[i].getStones() == 0){
        points[playerRank[i].getPlayer()] = 0;
      } else {
        points[playerRank[i].getPlayer()] = pointsPerRank[i];
      }
    }
    for(int i = 0; i < playerCount - 1; i++){
      int equalTo = checkSameAmount(i, playerRank, playerCount);
      int sum = points[playerRank[i].getPlayer()];
      for(int j = 1; j <= equalTo; j++){
        sum += points[playerRank[i + j].getPlayer()];
      }
      for(int j = 0; j <= equalTo; j++){
        points[playerRank[i + j].getPlayer()] = sum / (equalTo + 1);
      }
    }
    return points;
  }

  private int checkSameAmount(int player, ObeliskHelper[] playerRank, int playerCount) {
    if(player < playerCount - 1 && playerRank[player].getStones() == playerRank[player + 1].getStones()){
      int number = 1;
      number += checkSameAmount(player + 1, playerRank, playerCount);
      return number;
    }
    return 0;
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
