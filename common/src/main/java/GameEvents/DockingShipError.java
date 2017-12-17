package GameEvents;

import SRVevents.Event;

import java.util.Date;

/**
 * Created on 16.12.2017.
 */
public class DockingShipError implements Event {
  private String event = "DockingShipError";
  public Date date;

  public DockingShipError() {
    this.date = new Date();
  }

  public String getEvent() {
    return event;
  }

  public Date getDate() {
    return this.date;
  }
}
