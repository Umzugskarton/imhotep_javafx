package game.board;

import java.util.ArrayList;
import java.util.List;

public class BurialChamber extends Site
        implements StoneSite {


  private ArrayList<Stone>[] burialChamber;

  private int playerSize;

  public BurialChamber(int playerSize) {
    burialChamber = new ArrayList[3];
    this.playerSize = playerSize;
  }

  // TODO
  @Override
  public int[] getPoints() {
    ArrayList<Integer>[] pointC = new ArrayList[playerSize];
    boolean[][] visited = new boolean[3][burialChamber[0].size()];
    for (int pl = 0; pl < playerSize; pl++){
      for (int i = 0; i < burialChamber[0].size(); i++){
        for (int list = 0; list < 3; list++){
          if (burialChamber[list].get(i).getPlayer().getPlayerId() == pl){
            if (list != 0 ){
              if (burialChamber[list].get(i-1).getPlayer().getPlayerId() == pl){
                int lastC = pointC[pl].get(pointC[pl].size()-2);
                pointC[pl].set(pointC[pl].size()-2, lastC +1);
              }
              else{
                pointC[pl].add(1);
              }
            }
          }
        }
      }
    }

    int[] points = new int[playerSize];
    ArrayList<Integer>[][] placed = new ArrayList[playerSize][3];
    //Durch jede Liste iterieren der Burial chamber
    for (int Blist = 0; Blist < 3; Blist++ ){
      // Durch jedes Element der Liste iterieren
      for (int i = 0; i < burialChamber[Blist].size(); i++){
        if (burialChamber[Blist].get(i) != null) {
          /* für jeden Spieler werden die Indizes in dem placed ArrayList Array
              gespeichert auf dem er in dieser Teilliste der Burial chamber einen stein liegen hat
           */
          if (placed[burialChamber[Blist].get(i).getPlayer().getPlayerId()][Blist].get(i-1) != null){
            placed[burialChamber[Blist].get(i).getPlayer().getPlayerId()][Blist].add(i);
          }

        }
      }
    }
    // Berechnung der Punkte
    for (int i = 0; i < placed.length; i++){
      for(int j = 0; j < placed[i].length; j++){
        for (int k = 0; k < placed[i][j].size(); k++) {
          int adjacentTo = placed[i][j].get(k);
        }
      }
    }

  /*
  Bsp. Burial Chamber Punkteberechnung : {0,1,2} SpielerId´s
                     _______________________
    burialChamber[0]:|0| |0| |1| |1| |2| |2|
    burialChamber[1]:|1| |0| |0| |1| |1| |2|
    burialChamber[2]:|2| |0| |1| |2| |2| |1|
                     –––––––––––––––––––—---

    placed[player][List]:

     Player 0 : List 0 = {0,1}
                List 1 = {1,2}
                List 2 = {1}

     Player 1 : List 0 = {2,3}
                List 1 = {0,3,4}
                List 2 = {2,5}
     ...etc.





   */
    return new int[0];
  }

  @Override
  public ArrayList<Stone> getStones() {
    return burialChamber;
  }

  @Override
  public void addStones(ArrayList<Stone> stones) {

  }

  public boolean dockShip(Ship ship) {
    if (this.getDockedShip() != null) return false;
    addStones(ship.getStones());
    return true;
  }
}
