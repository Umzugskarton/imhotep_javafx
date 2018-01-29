package GameEvents;

import SRVevents.Event;

import java.util.Date;

/**
 * Created on 16.12.2017.
 */
public class ShipAlreadyDockedError implements Event {
  private String event = "ShipAlreadyDockedError";
  public Date date;
  private int shipID;

  public ShipAlreadyDockedError(int shipID) {
    this.shipID = shipID;
    this.date = new Date();
  }

  public int getShipID() {
    return shipID;
  }

  public String getEvent() {
    return event;
  }

  public Date getDate() {
    return this.date;
  }
}
