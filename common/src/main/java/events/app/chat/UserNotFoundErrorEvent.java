package events.app.chat;

import events.Event;

public class UserNotFoundErrorEvent extends Event {

  private String event = "userNotFoundError";
  private String msg;

  public UserNotFoundErrorEvent(){
    this.lobbyId = -1;
  }

  public void setLobbyId(int lobbyId) {
    this.lobbyId = lobbyId;
  }

  public void setMsg(String msg) {
    this.msg = "Der User " + msg + " ist momentan nicht eingeloggt.";
  }

  public String getMsg() {
    return this.msg;
  }
}
