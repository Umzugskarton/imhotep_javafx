package events.app.chat;

import events.Event;
import javafx.scene.paint.Color;

public class ChatInfoEvent extends Event {

  private String msg;
  private Color textColor = null;

  public ChatInfoEvent() {
  }

  public ChatInfoEvent(String message, Color textColor) {
    this.setMsg(message);
    this.setTextColor(textColor);
    this.lobbyId = -1;
  }

  public Color getTextColor() {
    return textColor;
  }

  public void setTextColor(Color textColor) {
    this.textColor = textColor;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public String getMsg() {
    return this.msg;
  }
}
