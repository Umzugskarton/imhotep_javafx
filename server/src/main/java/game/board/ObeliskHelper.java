package game.board;

/**
 * Created by Matthias on 10.11.2017.
 */
public class ObeliskHelper {
  private int player;
  private int stones;

  public ObeliskHelper(int player, int stones) {
    this.player = player;
    this.stones = stones;
  }

  public int getPlayer() {
    return player;
  }

  public int getStones() {
    return stones;
  }
}
