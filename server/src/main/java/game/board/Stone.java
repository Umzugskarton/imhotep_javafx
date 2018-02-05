package game.board;

import game.Player;

/**
 * Repräsentiert einen Stein im Besitz eines bestimmten Spielers.
 */
public class Stone {

  private Player owner;

  /**
   * Erstellt einen neuen Stein mit einem Besitzer.
   *
   * @param owner der besitzende Spieler
   */
  public Stone(Player owner) {
    this.owner = owner;
  }

  public Player getPlayer() {
    return this.owner;
  }
}
