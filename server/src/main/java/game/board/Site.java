package game.board;

public abstract class Site {

  private Ship dockedShip = null;

  final int playerCount;

  Site(int playerCount) {
    this.playerCount = playerCount;
  }

  public abstract boolean dockShip(Ship ship);

  public Ship getDockedShip() {
    return dockedShip;
  }

  public void setDockedShip(Ship dockedShip) { this.dockedShip = dockedShip; }

  public boolean isDocked(){ return dockedShip !=null;}

}
