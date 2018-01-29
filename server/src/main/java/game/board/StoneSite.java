package game.board;

import java.util.ArrayList;

public abstract class StoneSite extends Site implements IStoneSite {

  protected ArrayList<Stone> stoneSite = new ArrayList<>();

  StoneSite(int playerCount) {
    super(playerCount);
  }

  public void addStones(Stone[] stones){
    for (Stone stone : stones){
      if (stone !=null){
        stoneSite.add(stone);
      }
    }
  }

  public ArrayList<Stone> getStones() {
    return stoneSite;
  }

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

  public abstract int[] getPoints();


}
