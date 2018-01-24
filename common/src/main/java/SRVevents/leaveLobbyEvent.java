package SRVevents;

import java.util.Date;

/**
 * Created by Slothan on 18.12.2017.
 */
public class leaveLobbyEvent implements Event {

    private String event = "leaveLobby";
    private Date date;
    private String msg;
    private boolean success;

    public leaveLobbyEvent() {

    }

    public leaveLobbyEvent(boolean success) {
        this.success = success;
    }

    public Date getDate() {
        return this.date;
    }

    public String getMsg() {
        return this.msg;
    }

    public boolean getSuccess() {
        return this.success;
    }
}
