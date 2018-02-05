package game;

import data.user.User;
import game.board.cards.Card;
import java.util.ArrayList;
import java.util.List;

/**
 * Repräsentiert einen Spieler. Enthält seine Punktanzahl, seine aktuellen Karten und seine Steine.
 */
public class Player {

  private int id;
  private int points = 0;
  private User user;
  private List<Card> cards = new ArrayList<>();
  private SupplySled supplySled = new SupplySled();

  /**
   * Erstellt einen neuen Spieler.
   *
   * @param user der eingeloggte User, dem der Spieler zugeordnet ist
   * @param id die ID des Spielers im Spiel; "Spielernummer"
   */
  public Player(User user, int id) {
    this.user = user;
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public User getUser() {
    return user;
  }

  public void addPoints(int points) {
    this.points += points;
  }

  public void addCard(Card card) {
    cards.add(card);
  }

  public boolean ownsCard(Card card) {
    return cards.contains(card);
  }

  /**
   * Aktuelle Anzahl der Steine im Vorrat.
   *
   * @return Steinvorrat des besitzenden Spielers
   */
  public int getStones() {
    return supplySled.getStones();
  }

  /**
   * Fügt dem Vorrat die Standardanzahl neuer Steine hinzu.
   */
  public void addStones() {
    supplySled.addStones();
  }

  /**
   * @param amount die Anzahl der hinzuzufügenden Steine
   */
  public void addStones(int amount) {
    supplySled.addStones(amount);
  }

  /**
   * Entfernt einen Stein aus dem Vorrat.
   *
   * @return true, wenn noch mindestens ein Stein verfügbar war.
   */
  public boolean removeStone() {
    return supplySled.removeStone();
  }

  public int getPoints() {
    return points;
  }

  public SupplySled getSupplySled() {
    return supplySled;
  }

  /**
   * Versorgungsplättchen mit dem Steinvorrat eines Players.
   */
  private static class SupplySled {

    private static final int CAPACITY = 5;
    private static final int STANDARD_RESUPPLY = 3;
    private int stones = 0;

    /**
     * Aktuelle Anzahl der Steine auf dem Plättchen.
     *
     * @return Steinvorrat des besitzenden Spielers
     */
    int getStones() {
      return stones;
    }

    /**
     * @param amount die Anzahl der hinzuzufügenden Steine
     */
    void addStones(int amount) {
      stones = Math.min(stones + amount, CAPACITY);
    }

    /**
     * Fügt dem SupplySled die Standardanzahl neuer Steine hinzu.
     */
    void addStones() {
      addStones(STANDARD_RESUPPLY);
    }

    /**
     * Entfernt einen Stein vom SupplySled.
     *
     * @return true, wenn noch mindestens ein Stein verfügbar war.
     */
    boolean removeStone() {
      if (this.stones > 0) {
        this.stones--;
        return true;
      }
      return false;
    }
  }
}