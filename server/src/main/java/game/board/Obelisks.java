package game.board;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Respräsentiert einen Obelisken.
 *
 * Punkte am Ende des Spiels.
 */
public class Obelisks extends StoneSite {

  private int[] pointsForRank;

  /**
   * Erstellt einen neuen Obelisken.
   *
   * @param playerCount Anzahl der Spieler im Spiel
   */
  public Obelisks(int playerCount) {
    super(playerCount);
    setRankPoints();
  }

  private void setRankPoints() {
    pointsForRank = new int[playerCount];
    switch (playerCount) {
      case 2:
        pointsForRank[0] = 10;
        pointsForRank[1] = 1;
        break;
      case 3:
        pointsForRank[0] = 12;
        pointsForRank[1] = 6;
        pointsForRank[2] = 1;
        break;
      case 4:
        pointsForRank[0] = 15;
        pointsForRank[1] = 10;
        pointsForRank[2] = 5;
        pointsForRank[3] = 1;
        break;
      default:
        break;
    }
  }

  @Override
  public int[] getPoints() {
    int[] points = new int[playerCount];
    int[] stonesPerPlayer = new int[playerCount];
    for (Stone stone : stones) {
      stonesPerPlayer[stone.getPlayer().getId()]++;
    }
    ObeliskHelper[] playerRank = new ObeliskHelper[playerCount];
    /*Hier wird die Anzahl der Steine mit dem zugehörigen Spieler in ein Objekt gepackt, damit man, nachdem die Liste
    nach der Steinzahl sortiert wird, noch die Spieler zu der Anzahl der Steine zuordnen kann*/
    for (int i = 0; i < playerCount; i++) {
      playerRank[i] = new ObeliskHelper(i, stonesPerPlayer[i]);
    }
    Arrays.sort(playerRank, Comparator.comparing(ObeliskHelper::getStones).reversed());
    /*Sortiert das Array basierend auf der Anzahl der Steine der Spieler rückwärts. Das Ergebnis könnte bspw. so
    aussehen:
    Player : 3, Stones : 6
    Player : 1, Stones : 5
    Player : 2, Stones : 4
    Player : 0, Stones : 4*/
    for (int i = 0; i < playerCount; i++) {
      if (playerRank[i].getStones() == 0) {
        points[playerRank[i].getPlayer()] = 0; //0 Punkte, wenn keine Steine im Obelisken sind
      } else {
        points[playerRank[i]
            .getPlayer()] = pointsForRank[i]; //Punktzahl basierend auf Position im sortierten Array
      }
    }
    for (int i = 0; i < playerCount - 1; i++) {
      int equalTo = checkSameAmount(i, playerRank, playerCount);
      /*prüft rekursiv, ob weitere Spieler dieselbe Anzahl an Steinen haben und speichert die Anzahl dieser Spieler
      Dabei werden immer nur nachfolgende Spieler berücksichtigt, da die Gleichheit nicht doppelt festgestellt werden
      muss. Im obigen Beispiel wäre das:
      equalTo von Player 2 = 1,
      equalTo von Player 0 = 0*/
      int sum = points[playerRank[i].getPlayer()];
      for (int j = 1; j <= equalTo; j++) {
        sum += points[playerRank[i + j]
            .getPlayer()]; //summiert die Punktzahlen der Spieler mit Gleichstand
      }
      for (int j = 0; j <= equalTo; j++) {
        points[playerRank[i + j].getPlayer()] = sum / (equalTo + 1);
        //teilt die Summe der Punkte durch die Anzahl der beteiligten Spieler
      }
    }
    return points;
  }

  private int checkSameAmount(int player, ObeliskHelper[] playerRank, int playerCount) {
    if (player < (playerCount - 1) && playerRank[player].getStones() == playerRank[player + 1]
        .getStones()) {
      //es gibt einen nächsten Spieler und dieser hat gleich viele Steine
      int number = 1;
      number += checkSameAmount(player + 1, playerRank, playerCount);
      return number;
    }
    return 0;
  }

  private class ObeliskHelper {

    private int player;
    private int stones;

    private ObeliskHelper(int player, int stones) {
      this.player = player;
      this.stones = stones;
    }

    public int getPlayer() {
      return player;
    }

    public int getStones() {
      return stones;
    }
  }
}
