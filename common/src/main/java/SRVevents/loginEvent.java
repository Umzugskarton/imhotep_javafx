package SRVevents;

import org.json.simple.JSONObject;
import java.util.Date;

public class loginEvent implements voidEvent{
    private boolean success;
    private String msg;
    private Date date;

    public loginEvent(){
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
        if (j.containsKey("message") && j.containsKey("success")){
            this.msg =(String) j.get("message");
            this.success= (boolean) j.get("success");
        }else{
            this.success= false;
            this.msg= "Ein Fehler ist aufgetreten";

        }
    }
}
