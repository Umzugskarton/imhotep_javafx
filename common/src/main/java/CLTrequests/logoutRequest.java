package CLTrequests;

import java.util.Date;

/**
 * Created on 10.10.2017.
 */
public class logoutRequest implements Request {
    private String request = "logout";
    private Date date;

    public logoutRequest(){
        this.date = new Date();
    }

    public String getRequest(){
        return this.request;
    }

    public Date getDate(){
        return this.date;
    }
}
