package events.app.lobby;

import events.Event;

public class CreateLobbyEvent extends Event {

  private int id;
  private String msg;
  private boolean success;

  public CreateLobbyEvent(boolean success, int lobbyID, String msg) {
    this.success = success;
    this.id = lobbyID;
    this.msg = msg;
  }

  public boolean getSuccess() {
    return this.success;
  }

  public int getId() {
    return this.id;
  }

  public String getMsg() {
    return msg;
  }
}
