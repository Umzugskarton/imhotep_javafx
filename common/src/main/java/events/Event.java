package events;

import java.io.Serializable;

public class Event implements Serializable {

  private EventReason reason;
  protected int lobbyId;

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
}
