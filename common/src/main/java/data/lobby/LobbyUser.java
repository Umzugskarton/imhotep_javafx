package data.lobby;

import data.user.User;

import java.io.Serializable;

public class LobbyUser implements Serializable {

  private User user;
  private String color;
  private boolean ready;

  public LobbyUser(User user, String color, boolean ready) {
    this.user = user;
    this.color = color;
    this.ready = ready;
  }

  public boolean isReady() {
    return this.ready;
  }

  public String getUsername() {
    return this.user.getUsername();
  }

  public User getUser() { return this.user;}

  public String getColor() {
    return this.color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public void setReady(boolean ready) {
    this.ready = ready;
  }
}
