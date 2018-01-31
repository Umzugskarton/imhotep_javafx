package GameEvents;

import SRVevents.Event;

public class UpdatePointsEvent implements Event {

  private String event = "UpdatePoints";
  private int[] points;

  public UpdatePointsEvent(int[] points) {
    this.points = points;
  }

  public int[] getPoints() {
    return points;
  }

  public String getEvent() {
    return event;
  }
}