package events.app.lobby;

import events.Event;

public class JoinLobbyEvent extends Event {

  private String msg;
  private boolean success;

  public JoinLobbyEvent(String msg, boolean success) {
    this.msg = msg;
    this.success = success;
  }

  public String getMsg() {
    return this.msg;
  }

  public boolean getSuccess() {
    return this.success;
  }
}
