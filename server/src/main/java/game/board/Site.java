package game.board;

abstract class Site {
  private Ship dockedShip = null;

  abstract boolean dockShip(Ship ship);

  Ship getDockedShip() {
    return dockedShip;
  }

}
