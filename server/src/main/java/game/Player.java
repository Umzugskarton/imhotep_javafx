package game;


import user.User;

public class Player {
  private int playerId;
  private int points = 0;
  private User user;

  public Player(User user, int id) {
    this.user = user;
    this.playerId = id;
  }

  public int getPlayerId() {
    return playerId;
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
