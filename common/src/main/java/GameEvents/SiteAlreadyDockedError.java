package GameEvents;

import SRVevents.Event;

import java.util.Date;

/**
 * Created on 16.12.2017.
 */
public class SiteAlreadyDockedError implements Event {
  private String event = "ShipAlreadyDockedError";
  public Date date;
  private String site;

  public SiteAlreadyDockedError(String site) {
    this.site = site;
    this.date = new Date();
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
