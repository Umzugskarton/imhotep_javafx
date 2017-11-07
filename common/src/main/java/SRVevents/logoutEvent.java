package SRVevents;

import java.util.Date;

public class logoutEvent implements Event {
    private String event = "logout";
    private boolean success;
    private String msg;
    private Date date;

    public logoutEvent(){
        this.date = new Date();
    }

    public String getMsg(){
        return this.msg;
    }

    public boolean getSuccess(){
        return this.success;
    }

    public Date getDate() {
        return date;
    }


}
