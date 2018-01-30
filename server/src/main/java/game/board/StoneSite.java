package game.board;

import java.util.ArrayList;

/**
 * Repräsentiert eine StoneSite.
 */
public abstract class StoneSite extends Site implements IStoneSite {

  ArrayList<Stone> stoneSite = new ArrayList<>();

  StoneSite(int playerCount) {
    super(playerCount);
  }

  @Override
  public void addStones(Stone[] stones){
    for (Stone stone : stones){
      if (stone !=null){
        stoneSite.add(stone);
      }
    }
  }

  @Override
  public ArrayList<Stone> getStones() {
    return stoneSite;
  }

  @Override
  public boolean dockShip(Ship ship) {
    if (this.getDockedShip() != null) {
      return false;
    }
    this.setDockedShip(ship);
    addStones(ship.getStones());
    return true;
  }

  @Override
  public boolean isDocked(){
    return this.getDockedShip() != null;
  }

  @Override
  public abstract int[] getPoints();


}
