package events.app.game;

import java.util.ArrayList;

public class ShipDockedEvent extends GameEvent {

  private String site;
  private int shipID;
  private ArrayList<Integer> newstones;

  public ShipDockedEvent(int shipID, String site, ArrayList<Integer> newstones) {
    this.shipID = shipID;
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
}
