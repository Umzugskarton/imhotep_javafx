package main.events;

import org.json.simple.JSONObject;
import java.util.Date;


public class chatInfoEvent implements voidEvent{
    private Date date;
    private String msg;

    public chatInfoEvent(){
        this.date = new Date();
        this.msg=msg;
    }

    public String getMsg() {
        return this.msg;
    }

    public Date getDate() {
        return this.date;
    }

    public void init(JSONObject j){
        if (j.containsKey("message")){
            this.msg= (String) j.get("message");
        }else{
            this.msg= "Ein Fehler ist aufgetreten";
        }
    }
}
