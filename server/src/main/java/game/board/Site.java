package game.board;

public abstract class Site {
  private Ship dockedShip = null;

  abstract boolean dockShip(Ship ship);

  public Ship getDockedShip() {
    return dockedShip;
  }


}
