package CLTrequests;

import java.util.Date;

public class setReadyRequest implements Request {

    private String request = "setReady";
    private Date date;
    private String username;


    public String getType() {
        return this.request;
    }

}
