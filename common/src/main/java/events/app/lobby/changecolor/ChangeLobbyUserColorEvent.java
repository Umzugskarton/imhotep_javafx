package events.app.lobby.changecolor;

import events.Event;

public class ChangeLobbyUserColorEvent extends Event {

  private int userId;
  private String color;

  public ChangeLobbyUserColorEvent(int userId, String color, int lobbyId) {
    this.color = color;
    this.userId = userId;
    this.lobbyId = lobbyId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public int getUserId() {
    return userId;
  }

  public String getColor() {
    return this.color;
  }
}
