package SRVevents;

import java.util.Date;

public class chatEvent implements voidEvent {
    private String event = "chat";
    private Date date;
    private String user;
    private String msg;

    public chatEvent(){
     this.date= new Date();
    }

    public void setUser(String username) {this.user = username;}
    public void setMsg(String msg){this.msg = msg;}

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
