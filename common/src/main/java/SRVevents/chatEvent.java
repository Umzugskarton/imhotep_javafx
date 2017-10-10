package SRVevents;

import org.json.simple.JSONObject;
import java.util.Date;

public class chatEvent implements voidEvent {
    private Date date;
    private String user;
    private String msg;

    public chatEvent(){
     this.date= new Date();
    }

    public String getUser(){
        return this.user;
    }

    public String getMsg(){
        return this.msg;
    }

    public Date getDate(){
        return this.date;
    }

}
