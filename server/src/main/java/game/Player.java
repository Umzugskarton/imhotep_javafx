package game;

import data.User;

/**
 * Repräsentiert einen Spieler. Enthält seine Punktanzahl, seine aktuellen Karten und seine Steine.
 */
public class Player {

  private int id;
  private int points = 0;
  private User user;

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

  public int getPoints() {
    return points;
  }
}
