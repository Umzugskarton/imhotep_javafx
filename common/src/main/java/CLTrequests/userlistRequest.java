package CLTrequests;

import java.util.Date;

/**
 * Created on 10.10.2017.
 */
public class userlistRequest implements Request{
    private String request = "userlist";
    private Date date;

    public userlistRequest(){
        this.date = new Date();
    }

    public String getRequest(){
        return this.request;
    }

    public Date getDate(){
        return this.date;
    }
}
