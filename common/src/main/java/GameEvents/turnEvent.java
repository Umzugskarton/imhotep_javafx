package GameEvents;

import SRVevents.Event;

import java.util.Date;

public class turnEvent implements Event {
  boolean myturn;

  public turnEvent(boolean myturn) {
    this.myturn = myturn;
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
