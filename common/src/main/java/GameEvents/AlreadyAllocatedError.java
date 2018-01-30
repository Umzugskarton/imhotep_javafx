package GameEvents;

import SRVevents.Event;

public class AlreadyAllocatedError implements Event {

  private String event = "AlreadyAllocatedError";
  private int shipID;
  private int position;

  public AlreadyAllocatedError() {
  }

  public AlreadyAllocatedError(int shipID, int position) {
    this.shipID = shipID;
    this.position = position;
  }

  public int getPosition() {
    return position;
  }

  public int getShipID() {
    return shipID;
  }

  public String getEvent() {
    return event;
  }
}
