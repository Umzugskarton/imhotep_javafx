package GameEvents;

import SRVevents.Event;

public class NotEnoughLoadError implements Event {

  private String event = "NotEnoughLoadError";
  private int shipID;

  public NotEnoughLoadError(int shipID) {
    this.shipID = shipID;
  }

  public int getShipID() {
    return shipID;
  }

  public String getEvent() {
    return event;
  }
}