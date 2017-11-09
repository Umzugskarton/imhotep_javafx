package game.board;

import java.util.ArrayList;

public class BurialChamber extends Site
                           implements StoneSite {

  private ArrayList<Stone> burialChamber;

  // TODO
  //Ansatz: Iterieren über alle Elemente, gucken ob ein Element des selben Spielers daneben
  //liegt, mitzählen wie viele andere daneben lagen. Bei Treffer Zähler und ArrayList an
  //addPlayerStone übergeben, die dann bei kleinem Zähler (neuer Stein gehört zu ner bekannten Gruppe)
  //die Anzahl der Steine der aktuellen Gruppe erhöht, sonst eine neue Gruppe (neues Element der AL)
  //beginnt.
  //Funktioniert im Moment nicht und kann vermutlich grundsätzlich nicht funktionieren. Hässlich ists
  //auf jeden Fall.
  @Override
  public int[] getPoints() {
    ArrayList<ArrayList<Integer>> players = new ArrayList<>();
    players.add(new ArrayList<>());
    players.add(new ArrayList<>());
    players.add(new ArrayList<>());
    players.add(new ArrayList<>());
    int[] sinceLast = new int[4];
    for (int i = 0; i < burialChamber.size(); i++) {
      int currentPlayer = burialChamber.get(i).getPlayer().getPlayerId();
      int position = i%3;
      boolean helper = false;
      if (position < 2) {
        if (burialChamber.get(i+1).getPlayer().getPlayerId()==currentPlayer) {
          addPlayerStone(sinceLast[currentPlayer], players.get(currentPlayer));
          sinceLast[currentPlayer] = 0;
        } else {
          helper = true;
        }
        if (burialChamber.get(i+3).getPlayer().getPlayerId()==currentPlayer) {
          addPlayerStone(sinceLast[currentPlayer], players.get(currentPlayer));
          sinceLast[currentPlayer] = 0;
        } else {
          if (helper) {
            sinceLast[currentPlayer]++;
          }
        }
      } else if (position==2) {
        if (burialChamber.get(i+3).getPlayer().getPlayerId()==currentPlayer) {
          addPlayerStone(sinceLast[currentPlayer], players.get(currentPlayer));
          sinceLast[currentPlayer] = 0;
        } else {
          sinceLast[currentPlayer]++;
        }
      }

    }
    return new int[0];
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
  public void addStones(ArrayList<Stone> stones) {

  }

  public boolean dockShip(Ship ship) {
    if (this.getDockedShip() != null) return false;
    addStones(ship.getStones());
    return true;
  }
}
