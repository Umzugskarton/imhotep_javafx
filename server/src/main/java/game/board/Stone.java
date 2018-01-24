package game.board;

import game.Player;

public class Stone {

  private Player owner;

  public Stone(Player owner) {
    this.owner = owner;
  }

  public Player getPlayer() {
    return this.owner;
  }
}
