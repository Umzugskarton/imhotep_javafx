package CLTrequests;

import java.util.Date;

/**
 * Created on 10.10.2017.
 */
public class logoutRequest {
    private String command = "logout";
    private Date date;

    public logoutRequest(){
        this.date = new Date();
    }

    public String getCommand(){
        return this.command;
    }

    public Date getDate(){
        return this.date;
    }
}
