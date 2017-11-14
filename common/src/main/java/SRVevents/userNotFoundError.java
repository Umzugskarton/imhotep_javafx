package SRVevents;

import java.util.Date;

public class userNotFoundError implements Event {
    private String event = "userNotFoundError";
    private Date date;
    private String msg;

    public userNotFoundError() {
        this.date = new Date();
    }

    public void setMsg(String msg) {
        this.msg = "Der User " + msg + " ist momentan nicht eingeloggt.";
    }

    public String getMsg() {
        return this.msg;
    }

    public Date getDate() {
        return this.date;
    }


}