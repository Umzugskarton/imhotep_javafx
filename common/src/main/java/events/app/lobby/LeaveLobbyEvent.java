package events.app.lobby;

import events.Event;

public class LeaveLobbyEvent extends Event {

  private String msg;
  private boolean success;

  public LeaveLobbyEvent(boolean success, int lobbyId) {
    this.success = success;
    this.lobbyId = lobbyId;
  }

  public String getMsg() {
    return this.msg;
  }

  public boolean getSuccess() {
    return this.success;
  }
}
