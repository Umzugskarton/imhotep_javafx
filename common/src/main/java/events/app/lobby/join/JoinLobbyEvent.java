package events.app.lobby.join;

import events.Event;

public class JoinLobbyEvent extends Event {

  private String msg;
  private boolean success;

  public JoinLobbyEvent(String msg, boolean success, int lobbyId) {
    this.msg = msg;
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
