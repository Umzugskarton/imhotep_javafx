package game.board.Cards;

public class OrnamentCard extends Card {
  public OrnamentCard (String name) {
    this.setName(name);
  }

  public int calc(int[] stones) {
    int type = 0;
    if (this.getName().equals("pyramid")) {
      type = 0;
    } else if (this.getName().equals("temple")) {
      type = 1;
    } else if (this.getName().equals("burialchamber")) {
      type = 2;
    } else if (this.getName().equals("obelisk")) {
      type = 3;
    }
    return stones[type]/3;
  }
}
