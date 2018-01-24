package GameEvents;

import SRVevents.Event;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created on 16.12.2017.
 */
public class ShipDockedEvent implements Event {
  private String event = "ShipDocked";
  private String site;
  private int shipID;
  private ArrayList<Integer> newstones;

  public ShipDockedEvent() {

  }

  public ShipDockedEvent(int shipID, String site, ArrayList<Integer> newstones) {
    this.shipID= shipID;
    this.site = site;
    this.newstones = newstones;
  }

  public ArrayList<Integer> getNewstones() {
    return newstones;
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
}
