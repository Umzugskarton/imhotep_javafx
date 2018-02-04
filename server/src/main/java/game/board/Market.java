package game.board;

import game.board.cards.Card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Repräsentiert einen Markt. Wird hier ein Schiff angedockt, können die Spieler mit
 * Steinen auf den Schiff sich eine der aktuell ausliegenden Karten des Markts aussuchen.
 */
public class Market extends Site {

  private static final int VISIBLE_CARDS = 4;

  private ArrayList<Card> drawPile = new ArrayList<>();
  private Card[] activeCards = new Card[VISIBLE_CARDS];

  /**
   * Erstellt einen neuen Markt mit den gegebenen Karten und gibt ihn zurück.
   * @param playerCount die Anzahl der Spieler im Spiel.
   * @param cards die Karten, die der Markt als Nachziehstapel nutzt.
   */
  public Market(int playerCount, List<Card> cards) {
    super(playerCount);
    addCards(cards);
  }

  /**
   * Fügt die übergebenen Karten dem Kartenvorrat des Markts hinzu.
   *
   * @param cards die Karten, die der Markt seinem Kartenvorrat hinzufügt
   */
  private void addCards(List<Card> cards) {
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
  public List<Card> getActiveCards() {
    return new ArrayList<>(Arrays.asList(activeCards));
  }

  /**
   * Richtet den Markt für eine neue Runde ein. Noch vorhandene Karten werden entfernt
   * und aus dem Pool werden neue Karten gezogen.
   */
  public void newRound() {
    for (int i = 0; i < VISIBLE_CARDS; i++) {
      if (!drawPile.isEmpty()) {
        activeCards[i] = drawPile.remove(0);
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


