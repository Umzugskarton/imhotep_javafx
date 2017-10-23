package game.board;

public abstract class Site {
  private Ship dockedShip = null;

  protected Ship getDockedShip() {
    return dockedShip;
  }

  protected boolean dockShip(Ship ship) {
    if (dockedShip != null) return false;
    this.dockedShip = ship;
    return true;
  }
}
