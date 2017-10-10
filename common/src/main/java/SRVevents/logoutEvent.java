package SRVevents;


import org.json.simple.JSONObject;
import java.util.Date;

public class logoutEvent implements voidEvent{
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

    public void init(JSONObject j){
        if (j.containsKey("success")) {
            this.success = (boolean) j.get("success");
        }
    }
}
