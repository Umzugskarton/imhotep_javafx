package SRVevents;

import java.util.Date;

/**
 * Created by Slothan on 23.10.2017.
 */
public class changeCredentialEvent implements Event {
    private String event = "changeCredential";
    private Date date;
    private String msg;
    private boolean validate;
    private String email;

    public changeCredentialEvent() { this.date = new Date(); }
    public void setMsg(String msg) { this.msg = msg; }
    public void setSuccess(boolean validate) { this.validate = validate; }
    public void setEmail(String email) { this.email = email; }
    public String getMsg() { return this.msg; }
    public Date getDate() { return this.date; }
    public boolean getSuccess() { return this.validate; }
    public String getEmail() { return this.email; }

}
