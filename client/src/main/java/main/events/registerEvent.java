package main.events;

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

    public void init(JSONObject j){
        if (j.containsKey("message")){
            this.msg=(String) j.get("message");
        }else{
            this.msg= "Ein Fehler ist aufgetreten";
        }
    }
}
