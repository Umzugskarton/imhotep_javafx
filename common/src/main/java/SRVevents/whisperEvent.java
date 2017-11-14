package SRVevents;

import java.util.Date;

public class whisperEvent implements Event {
    private String event = "whisper";
    private String from;
    private String msg;
    private String to;
    private Date date;

    public whisperEvent() {
        this.date = new Date();
    }

    public void setTo(String username) {
        this.to = username;
    }

    public void setFrom(String username) {
        this.from = username;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTo() {
        return this.to;
    }

    public String getFrom() {
        return this.from;
    }

    public String getMsg() {
        return this.msg;
    }

    public Date getDate() {
        return this.date;
    }

}