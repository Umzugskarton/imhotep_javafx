package CLTrequests;

import java.util.Date;

/**
 * Created by Slothan on 16.11.2017.
 */
public class setReadyRequest implements Request {

    private String request = "setReady";
    private Date date;
    private String username;


    public String getRequest() {
        return this.request;
    }

    public Date getDate() {
        return this.date;
    }
}
