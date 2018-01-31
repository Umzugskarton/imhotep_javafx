package GameEvents;

import SRVevents.Event;

public class DockingShipError implements Event {

  private String event = "DockingShipError";

  public String getEvent() {
    return event;
  }
}
