package SRVevents;

import java.util.Date;

/**
 * Created by Slothan on 16.11.2017.
 */
public class setReadyEvent implements Event {

    private String event = "setReady";
    private Date date;
    private String msg;

    public setReadyEvent() {

    }

    public String getEvent() {
        return this.event;
    }

    public Date getDate() {
        return this.date;
    }


}
