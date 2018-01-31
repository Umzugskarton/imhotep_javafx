package GameEvents;

import SRVevents.Event;

public class ShipAlreadyDockedError implements Event {

  private String event = "ShipAlreadyDockedError";
  private int shipID;

  public ShipAlreadyDockedError(int shipID) {
    this.shipID = shipID;
  }

  public int getShipID() {
    return shipID;
  }

  public String getEvent() {
    return event;
  }
}
