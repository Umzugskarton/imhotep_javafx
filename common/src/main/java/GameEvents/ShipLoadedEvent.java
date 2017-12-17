package GameEvents;

import SRVevents.Event;

import java.util.Date;

/**
 * Created on 17.12.2017.
 */
public class ShipLoadedEvent implements Event{
  private String event = "ShipDocked";
  public Date date;

  private int shipID;
  private int[] load;

  public ShipLoadedEvent(int shipID, int[] load) {
    this.shipID= shipID;
    this.load = load;
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
