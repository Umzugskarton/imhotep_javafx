package game;

import data.user.User;
import game.board.SupplySled;

/**
 * Repräsentiert einen Spieler. Enthält seine Punktanzahl, seine aktuellen Karten und seine Steine.
 */
public class Player {

  private int id;
  private int points = 0;
  private User user;
  private Inventory inventory;
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
    inventory = new Inventory();
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

  public Inventory getInventory() {
    return inventory;
  }

  public SupplySled getSupplySled() {
    return supplySled;
  }
}