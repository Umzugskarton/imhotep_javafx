package commonLobby;

import java.io.Serializable;

public class LobbyUser implements Serializable {

  private String username;
  private String color;
  private boolean ready;

  public LobbyUser(String username, String color, boolean ready) {
    this.username = username;
    this.color = color;
    this.ready = ready;
  }

  public boolean isReady() {
    return this.ready;
  }

  public String getUsername() {
    return this.username;
  }

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
