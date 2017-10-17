package CLTrequests;

import java.util.Date;

/**
 * Created on 13.10.2017.
 */
public class lobbylistRequest implements Request{
    private String request = "lobbylist";
    private Date date;

    public lobbylistRequest(){
        this.date = new Date();
    }

    public String getRequest(){
        return this.request;
    }

    public Date getDate(){
        return this.date;
    }

}
