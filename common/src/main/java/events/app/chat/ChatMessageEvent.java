package events.app.chat;

import events.Event;

public class ChatMessageEvent extends Event {

  private String user;
  private Integer lobbyId;
  private String msg;

  public ChatMessageEvent() {
    lobbyId = null;
  }

  public Integer getLobbyId() {
    return lobbyId;
  }

  public void setLobbyId(Integer lobbyId) {
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
