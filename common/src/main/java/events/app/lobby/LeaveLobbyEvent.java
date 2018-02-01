package events.app.lobby;

import events.Event;

public class LeaveLobbyEvent extends Event {

  private String msg;
  private boolean success;

  public LeaveLobbyEvent(boolean success) {
    this.success = success;
  }

  public String getMsg() {
    return this.msg;
  }

  public boolean getSuccess() {
    return this.success;
  }
}
