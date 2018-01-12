package game.board;

abstract class Site {

  private Ship dockedShip = null;

  final int playerCount;

  Site(int playerCount) {
    this.playerCount = playerCount;
  }

  abstract boolean dockShip(Ship ship);

  Ship getDockedShip() {
    return dockedShip;
  }

  void setDockedShip(Ship dockedShip){
    this.dockedShip = dockedShip;
  }

  boolean isDocked(){ return dockedShip !=null;}

}
