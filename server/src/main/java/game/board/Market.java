package game.board;

import java.util.ArrayList;

public class Market extends Site {

  public Market(int playerCount) {
    super(playerCount);
  }

  private ArrayList<Card> market = new ArrayList<>();

  public void replaceCards(ArrayList<Card> cards) {
    this.market = cards;
  }

  public Card removeCard(int position) {
    return market.remove(position);
  }

  public ArrayList<Card> getCards() {
    return market;
  }

  // TODO
  @Override
  public boolean dockShip(Ship ship) {
    return false;
  }
}
