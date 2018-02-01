package events.app.lobby;

import events.Event;

public class ChangeLobbyUserColorEvent extends Event {

  private int id;
  private String color;

  public ChangeLobbyUserColorEvent(int id, String color) {
    this.color = color;
    this.id = id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public String getColor() {
    return this.color;
  }
}
