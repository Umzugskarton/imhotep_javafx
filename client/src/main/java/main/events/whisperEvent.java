package main.events;

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

    public void init(JSONObject j){
        if (j.containsKey("message") && j.containsKey("from")) {
            String msg = (String) j.get("message");
            String from = (String) j.get("from");
            this.msg = msg;
            this.from = from;
        }else{
            this.from = "SYSTEM";
            this.msg = "Ein Fehler ist aufgetreten ";
        }
    }
}
