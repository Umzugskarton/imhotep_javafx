package GameEvents;

import SRVevents.Event;

import java.util.Date;

public class turnEvent implements Event {
  boolean myturn;
  String username;

  public turnEvent(boolean myturn, String username) {
    this.myturn = myturn;
    this.username = username;
  }

  public boolean isMyturn() {
    return myturn;
  }

  public void setMyturn(boolean myturn) {
    this.myturn = myturn;
  }

  @Override
  public Date getDate() {
    return this.date;
  }
}
