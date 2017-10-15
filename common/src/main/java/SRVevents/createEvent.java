package SRVevents;

import java.util.Date;

/**
 * Created on 13.10.2017.
 */
public class createEvent implements Event {
    private String event = "create";
    private int id;
    private String msg;
    private boolean success;
    private Date date;

    public  createEvent(){}

    public createEvent(boolean success, int lobbyID, String msg){
        this.success=success;
        this.id = lobbyID;
        this.msg= msg;
        this.date = new Date();
    }

    public boolean getSuccess(){
        return this.success;
    }

    public int getId(){
        return this.id;
    }

    public String getEvent() {
        return event;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public Date getDate() {
        return this.date;
    }
}
