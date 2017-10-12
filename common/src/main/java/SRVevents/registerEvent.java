package SRVevents;

import org.json.simple.JSONObject;
import java.util.Date;


public class registerEvent implements voidEvent {
    private Date date;
    private String msg;
    private boolean validate;

    public registerEvent(){
        this.date = new Date();
    }

    public void setMsg(String msg){
        this.msg=msg;
    }

    public void setSuccess(boolean validate){
        this.validate = validate;
    }
    public String getMsg(){
        return this.msg;
    }

    public Date getDate() {
        return this.date;
    }


}
