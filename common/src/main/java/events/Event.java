package events;

import java.io.Serializable;
import java.util.Date;

public abstract class Event implements Serializable {

  private EventReason reason;
  private int lobbyId;
  private final Date date;

  public Event(){
    this.date = new Date();
  }

  public void setReason(EventReason reason) {
    this.reason = reason;
  }

  public EventReason getReason() {
    return this.reason;
  }

  public int getLobbyId() {
    return lobbyId;
  }

  public void setLobbyId(int lobbyId) {
    this.lobbyId = lobbyId;
  }

  public Date getDate() {
    return date;
  }
}
