package GameEvents;

import SRVevents.Event;

import java.util.Date;

/**
 * Created on 16.12.2017.
 */
public class ShipDockedEvent implements Event {
  private String event = "ShipDocked";
  public Date date;
  private String site;
  private int shipID;
  private int[] newpoints;

  public ShipDockedEvent(int shipID, String site, int[] newpoints) {
    this.shipID= shipID;
    this.site = site;
    this.newpoints = newpoints;
    this.date = new Date();
  }

  public int[] getNewpoints() {
    return newpoints;
  }

  public int getShipID() {
    return shipID;
  }

  public String getSite() {
    return site;
  }

  public String getEvent() {
    return event;
  }

  public Date getDate() {
    return this.date;
  }
}
