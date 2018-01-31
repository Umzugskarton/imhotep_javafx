package game.board;

/**
 * Abstrakte Basisklasse für Sites.
 *
 * Klasse, die grundlegende Funktionen für alle Sites bietet, wie das Andocken von Schiffen
 * und die Anzahl der Spieler im Spiel.
 */
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

  void setDockedShip(Ship dockedShip) {
    this.dockedShip = dockedShip;
  }

  public boolean isDocked() {
    return dockedShip != null;
  }

}
