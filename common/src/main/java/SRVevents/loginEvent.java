package SRVevents;

import java.util.Date;

public class loginEvent implements Event {
    private String event = "login";
    private boolean success;
    private String msg;
    private Date date;

    public loginEvent() {
        this.date = new Date();

    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }

    public boolean getSuccess() {
        return this.success;
    }

    public Date getDate() {
        return date;
    }

}
