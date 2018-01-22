package game;

import user.User;

public class Player {

  private int id;
  private int points = 0;
  private User user;
  private Inventory inventory;

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
}
