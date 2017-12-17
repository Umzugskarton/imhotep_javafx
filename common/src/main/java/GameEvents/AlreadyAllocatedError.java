package GameEvents;

import SRVevents.Event;

import java.util.Date;

/**
 * Created on 17.12.2017.
 */
public class AlreadyAllocatedError implements Event {
  private String event = "AlreadyAllocatedError";
  public Date date;
  private int shipID;
  private int position;

  public AlreadyAllocatedError(int shipID, int position) {
    this.shipID= shipID;
    this.position = position;
    this.date = new Date();
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

  public Date getDate() {
    return this.date;
  }
}
