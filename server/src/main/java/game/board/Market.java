package game.board;

import java.util.ArrayList;

public class Market extends Site implements  StoneSite{

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

  @Override
  public int[] getPoints() {
    return new int[0];
  }

  @Override
  public void addStones(Stone[] stones) {

  }

  @Override
  public ArrayList<Stone> getStones() {
    return null;
  }

  // TODO
  @Override
  public boolean dockShip(Ship ship) {
    return false;
  }

  @Override
  public boolean isDocked(){
    return this.getDockedShip() != null;
  }

  @Override
  public Ship getDockedShip() {
    return super.getDockedShip();
  }
}
