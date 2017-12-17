package GameEvents;

import SRVevents.Event;

import java.util.Date;

public class FillUpStorageEvent implements Event {
  private String event = "filUpStorage";
  private int playerId;
  private boolean[] storage;
  public Date date;

  public FillUpStorageEvent() {
    this.date = new Date();
  }

  public FillUpStorageEvent(int playerId, boolean[] storage) {
    this.playerId = playerId;
    this.storage = storage;
    this.date = new Date();
  }

  public boolean[] getStorage() {
    return storage;
  }

  public String getEvent() {
    return event;
  }

  public void setStorage(boolean[] storage) {
    this.storage = storage;
  }

  public Date getDate() {
    return this.date;
  }
}
