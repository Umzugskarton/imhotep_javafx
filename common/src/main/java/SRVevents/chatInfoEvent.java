package SRVevents;

import java.util.Date;


public class chatInfoEvent implements voidEvent{
    private String event = "chatInfo";
    private Date date;
    private String msg;

    public chatInfoEvent(){
        this.date = new Date();
    }

    public void setMsg(String msg){this.msg = msg;}
    public String getMsg() {
        return this.msg;
    }

    public Date getDate() {
        return this.date;
    }

}
