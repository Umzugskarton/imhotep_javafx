package SRVevents;

import java.util.Date;

/**
 * Created by Slothan on 23.10.2017.
 */
public class changeEmailEvent implements Event {
    private String event = "changeEmail";
    private Date date;
    private String msg;
    private boolean validate;

    public changeEmailEvent() { this.date = new Date(); }
    public void setMsg(String msg) { this.msg = msg; }
    public void setSucess(boolean validate) { this.validate = validate; }
    public String getMsg() { return this.msg; }
    public Date getDate() { return this.date; }

}
