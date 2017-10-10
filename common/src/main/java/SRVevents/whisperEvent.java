package SRVevents;

import org.json.simple.JSONObject;
import java.util.Date;

public class whisperEvent implements voidEvent{
    private String from;
    private String msg;
    private Date date;

    public whisperEvent(){
        this.date = new Date();
    }

    public String getFrom(){
        return this.from;
    }

    public String getMsg() {
        return this.msg;
    }

    public Date getDate() {
        return this.date;
    }

}
