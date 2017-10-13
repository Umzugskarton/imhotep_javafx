package SRVevents;


import org.json.simple.JSONObject;
import java.util.Date;

public class logoutEvent implements voidEvent{
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
