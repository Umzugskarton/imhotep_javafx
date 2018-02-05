package events.app.lobby;

import events.Event;

public class ChangeLobbyUserColorEvent extends Event {

  private String color;

  public ChangeLobbyUserColorEvent(int id, String color) {
    this.color = color;
    this.lobbyId = id;
  }

  public void setId(int id) {
    this.lobbyId = id;
  }


  public String getColor() {
    return this.color;
  }
}
