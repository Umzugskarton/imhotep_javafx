package SRVevents;

import java.util.Date;

/**
 * Created by Slothan on 16.11.2017.
 */
public class setReadyEvent implements Event {

    private String event = "setReady";
    private Date date;
    private String msg;
    private boolean[] ready;

    public setReadyEvent() {

    }

    public setReadyEvent(boolean[] ready) {
        this.ready = ready;
    }

    public String getEvent() {
        return this.event;
    }

    public Date getDate() {
        return this.date;
    }

    public String getMsg() { return this.msg; }

    public boolean[] getReady() { return this.ready; }


}
