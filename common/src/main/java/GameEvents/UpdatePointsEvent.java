package GameEvents;

import SRVevents.Event;

import java.util.ArrayList;
import java.util.Date;


public class UpdatePointsEvent implements Event {
    private String event = "UpdatePoints";
    private int[] points;

    public UpdatePointsEvent() {

    }

    public UpdatePointsEvent(int[] points) {
        this.points = points;
    }

    public int[] getPoints(){
        return points;
    }

    public String getEvent() {
        return event;
    }
}