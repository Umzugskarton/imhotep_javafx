package game.board;

import game.board.Cards.Card;
import java.util.ArrayList;
import java.util.Arrays;

public class Market extends Site implements StoneSite {
  private static final int numberOfCards = 4;

  private ArrayList<Card> drawPile = new ArrayList<>();
  private Card[] activeCards = new Card[numberOfCards];

  public Market(int playerCount) {
    super(playerCount);
  }

  public void addCards(ArrayList<Card> cards) {
    drawPile.addAll(cards);
  }

  /**
   * Gibt die aktive Karte an Index position zurück.
   * @param position
   * @return
   */
  public Card removeCard(int position) {
    // TODO exception handling: activeCards[position] might not hold a card
    Card c = activeCards[position];
    activeCards[position] = null;
    return c;
  }

  /**
   * Gibt eine begrenzte Anzahl Karten zurück, nämlich genau die, die zum aktuellen
   * Zeitpunkt auf dem Markt angezeigt werden.
   *
   * @return the currently relevant cards
   */
  public ArrayList<Card> getActiveCards() {
    return new ArrayList<>(Arrays.asList(activeCards));
  }

  /**
   * Richtet den Markt für eine neue Runde ein. Noch vorhandene Karten werden entfernt
   * und aus dem Pool werden neue Karten gezogen.
   */
  public void newRound() {
    for (int i = 0; i < numberOfCards; i++) {
      if (!drawPile.isEmpty()) {
        activeCards[i] = drawPile.get(0);
      }
    }
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
  public boolean isDocked() {
    return this.getDockedShip() != null;
  }

  @Override
  public Ship getDockedShip() {
    return super.getDockedShip();
  }
}
