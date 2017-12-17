package GameEvents;

import SRVevents.Event;

import java.util.Date;

public class TurnEvent implements Event {
  private String event = "turn";
  boolean myturn;
  String username;

  // Dummy
  public TurnEvent() { }

  public TurnEvent(boolean myturn, String username) {
    this.myturn = myturn;
    this.username = username;
  }

  public boolean isMyturn() {
    return myturn;
  }

  public void setMyturn(boolean myturn) {
    this.myturn = myturn;
  }

  public String getUsername() {
    return this.username;
  }

  @Override
  public Date getDate() {
    return this.date;
  }
}
