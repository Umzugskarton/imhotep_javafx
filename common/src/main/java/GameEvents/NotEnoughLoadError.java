package GameEvents;

import SRVevents.Event;

import java.util.Date;


public class NotEnoughLoadError implements Event {
    private String event = "NotEnoughLoadError";
    public Date date;
    private int shipID;

    public NotEnoughLoadError(int shipID) {
        this.shipID = shipID;
        this.date = new Date();
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