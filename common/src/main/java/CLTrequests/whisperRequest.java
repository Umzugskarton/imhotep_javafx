package CLTrequests;

import java.util.Date;

/**
 * Created on 10.10.2017.
 */
public class whisperRequest {
    String command ="whisper";
    private Date date;
    private String to;
    private String msg;

    public whisperRequest(String to, String msg){
        this.to = to;
        this.msg = msg;
        this.date = new Date();
    }

    public String getCommand(){
        return this.command;
    }

    public String getRecipient(){
        return this.to;
    }

    public String getMsg(){
        return this.msg;
    }

    public Date getDate(){
        return this.date;
    }
}
