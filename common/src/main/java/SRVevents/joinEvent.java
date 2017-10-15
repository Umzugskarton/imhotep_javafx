package SRVevents;

import java.util.Date;

/**
 * Created on 13.10.2017.
 */
public class joinEvent implements Event {
    private String request = "join";
    private Date date;
    private String msg;
    private boolean success;

    public joinEvent(){

    }

    public joinEvent(String msg, boolean success){
        this.msg = msg;
        this.success = success;
        this.date = new Date();
    }

    public String getRequest(){
        return this.request;
    }

    public String getMsg(){
        return this.msg;
    }

    public boolean getSuccess(){
        return this.success;
    }

    public Date getDate(){
        return this.date;
    }
}
