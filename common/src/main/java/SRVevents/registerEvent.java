package SRVevents;

import org.json.simple.JSONObject;
import java.util.Date;


public class registerEvent implements voidEvent {
    private Date date;
    private String msg;

    public registerEvent(){
        this.date = new Date();
    }

    public String getMsg(){
        return this.msg;
    }

    public Date getDate() {
        return this.date;
    }


}
