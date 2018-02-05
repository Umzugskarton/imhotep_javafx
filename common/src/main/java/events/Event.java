package events;

import java.io.Serializable;

public class Event implements Serializable {

  private EventReason reason;
  protected Integer lobbyId;

  public void setReason(EventReason reason) {
    this.reason = reason;
  }

  public EventReason getReason() {
    return this.reason;
  }

  public Integer getLobbyId() {
    return lobbyId;
  }
}
