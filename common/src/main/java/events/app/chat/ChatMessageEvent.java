package events.app.chat;

import events.Event;

public class ChatMessageEvent extends Event {

  private String user;
  private String msg;

  public ChatMessageEvent() {
    setLobbyId(-1);
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