package game.board;

public abstract class Site {
  private Ship dockedShip = null;

  public Ship getDockedShip() {
    return dockedShip;
  }

  public boolean dockShip(Ship ship) {
    if (dockedShip != null) return false;
    this.dockedShip = ship;
    return true;
  }
}
