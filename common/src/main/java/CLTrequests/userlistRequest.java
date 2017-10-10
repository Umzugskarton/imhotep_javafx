package CLTrequests;

import java.util.Date;

/**
 * Created on 10.10.2017.
 */
public class userlistRequest {
    private String command = "userlist";
    private Date date;

    public userlistRequest(){
        this.date = new Date();
    }

    public String getCommand(){
        return this.command;
    }

    public Date getDate(){
        return this.date;
    }
}
