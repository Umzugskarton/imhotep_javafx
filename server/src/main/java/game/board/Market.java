package game.board;

import game.board.Cards.Card;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Repräsentiert einen Markt. Wird hier ein Schiff angedockt, können die Spieler mit
 * Steinen auf den Schiff sich eine der aktuell ausliegenden Karten des Markts aussuchen.
 */
public class Market extends Site {

  private static final int numberOfCards = 4;

  private ArrayList<Card> drawPile = new ArrayList<>();
  private Card[] activeCards = new Card[numberOfCards];

  /**
   * Erstellt einen neuen Markt und gibt ihn zurück.
   *
   * @param playerCount die Anzahl der Spieler im Spiel.
   */
  public Market(int playerCount) {
    super(playerCount);
  }

  /**
   * Erstellt einen neuen Markt mit den gegebenen Karten und gibt ihn zurück.
   * @param playerCount die Anzahl der Spieler im Spiel.
   * @param cards die Karten, die der Markt als Nachziehstapel nutzt.
   */
  public Market(int playerCount, ArrayList<Card> cards) {
    super(playerCount);
    addCards(cards);
  }

  /**
   * Fügt die übergebenen Karten dem Kartenvorrat des Markts hinzu.
   *
   * @param cards die Karten, die der Markt seinem Kartenvorrat hinzufügt
   */
  public void addCards(ArrayList<Card> cards) {
    drawPile.addAll(cards);
    Collections.shuffle(drawPile);
  }

  /**
   * Gibt die aktive Karte an Index position zurück.
   *
   * @param position die Position der zu entfernenden Karte
   * @return die gewählte Karte
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
   * @return die aktuell angezeigten Karten
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

  // TODO
  @Override
  public boolean dockShip(Ship ship) {
    if (this.getDockedShip() != null) {
      return false;
    }
    this.setDockedShip(ship);
    return true;
  }
}
