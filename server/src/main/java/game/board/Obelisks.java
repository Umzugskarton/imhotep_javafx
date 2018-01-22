package game.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Obelisks extends StoneSite {

  private ArrayList<Stone> obelisks = new ArrayList<>();

  public Obelisks(int playerCount) {
    super(playerCount);
  }

  @Override
  public ArrayList<Stone> getStones() {
    return obelisks;
  }

  @Override
  public int[] getPoints() {
    int[] points = new int[playerCount];
    int[] pointsPerRank = new int[playerCount];
    /*Hier werden die Punktzahlen pro Rang gespeichert. Diese hängen von der Anzahl der Spieler ab.
    Bei 2 Spielern bspw. 10 Punkte für Platz 1 und 1 Punkt für Platz 2*/
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
    /*Hier werden die Steine aus der Liste dem jeweiligen Spieler zugeordnet und gezählt.
    Bspw. sind stonesPerPlayer[0] die Steine, die der Spieler mit der ID 0 auf seinem Obelisken hat*/
    for(Stone stone : obelisks){
      stonesPerPlayer[stone.getPlayer().getId()]++;
    }
    ObeliskHelper[] playerRank = new ObeliskHelper[playerCount];
    /*Hier wird die Anzahl der Steine mit dem zugehörigen Spieler in ein Objekt gepackt, damit man, nachdem die Liste
    nach der Steinzahl sortiert wird, noch die Spieler zu der Anzahl der Steine zuordnen kann*/
    for(int i = 0; i < playerCount; i++){
      playerRank[i] = new ObeliskHelper(i, stonesPerPlayer[i]);
    }
    Arrays.sort(playerRank, Comparator.comparing(ObeliskHelper::getStones).reversed());
      /*Sortiert das Array basierend auf der Anzahl der Steine der Spieler rückwärts. Das Ergebnis könnte bspw. so
    aussehen:
    Player : 3, Stones : 6
    Player : 1, Stones : 5
    Player : 2, Stones : 4
    Player : 0, Stones : 4*/
    for(int i = 0; i < playerCount; i++){
      if(playerRank[i].getStones() == 0){
        points[playerRank[i].getPlayer()] = 0; //0 Punkte, wenn keine Steine im Obelisken sind
      } else {
        points[playerRank[i].getPlayer()] = pointsPerRank[i]; //Punktzahl basierend auf Position im sortierten Array
      }
    }
    for(int i = 0; i < playerCount - 1; i++){
      int equalTo = checkSameAmount(i, playerRank, playerCount);
      /*prüft rekursiv, ob weitere Spieler dieselbe Anzahl an Steinen haben und speichert die Anzahl dieser Spieler
      Dabei werden immer nur nachfolgende Spieler berücksichtigt, da die Gleichheit nicht doppelt festgestellt werden
      muss. Im obigen Beispiel wäre das:
      equalTo von Player 2 = 1,
      equalTo von Player 0 = 0*/
      int sum = points[playerRank[i].getPlayer()];
      for(int j = 1; j <= equalTo; j++){
        sum += points[playerRank[i + j].getPlayer()]; //summiert die Punktzahlen der Spieler mit Gleichstand
      }
      for(int j = 0; j <= equalTo; j++){
        points[playerRank[i + j].getPlayer()] = sum / (equalTo + 1);
        //teilt die Summe der Punkte durch die Anzahl der beteiligten Spieler
      }
    }
    return points;
  }

  private int checkSameAmount(int player, ObeliskHelper[] playerRank, int playerCount) {
    if(player < playerCount - 1 && playerRank[player].getStones() == playerRank[player + 1].getStones()){
        //es gibt einen nächsten Spieler und dieser hat gleich viele Steine
      int number = 1;
      number += checkSameAmount(player + 1, playerRank, playerCount);
      return number;
    }
    return 0;
  }

  @Override
  public void addStones(Stone[] stones) {
    for (Stone stone : stones){
      if (stone !=null){
        obelisks.add(stone);
      }
    }
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
