
package events.app.chat;

import events.Event;

public class WhisperChatEvent extends Event {

  private String from;
  private String msg;
  private String to;

  public void setTo(String username) {
    this.to = username;
  }

  public void setFrom(String username) {
    this.from = username;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public String getTo() {
    return this.to;
  }

  public String getFrom() {
    return this.from;
  }

  public String getMsg() {
    return this.msg;
  }
}
