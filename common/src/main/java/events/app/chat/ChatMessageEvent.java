package events.app.chat;

import events.Event;

public class ChatMessageEvent extends Event {

  private String user;
  private String msg;

  public ChatMessageEvent() {
    this.lobbyId=-1;
  }

  public void setLobbyId(int lobbyId) {
    this.lobbyId = lobbyId;
  }

  public void setUser(String username) {
    this.user = username;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public String getUser() {
    return this.user;
  }

  public String getMsg() {
    return this.msg;
  }
}