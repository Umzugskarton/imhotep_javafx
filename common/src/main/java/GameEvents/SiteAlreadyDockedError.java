package GameEvents;

import SRVevents.Event;

public class SiteAlreadyDockedError implements Event {

  private String event = "ShipAlreadyDockedError";
  private String site;

  public SiteAlreadyDockedError(String site) {
    this.site = site;
  }

  public String getSite() {
    return site;
  }

  public String getEvent() {
    return event;
  }
}
