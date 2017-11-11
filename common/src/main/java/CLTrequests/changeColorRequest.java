package CLTrequests;

import java.util.Date;

/**
 * Created by Slothan/Dennis Lindt on 09.11.2017.
 */
public class changeColorRequest implements Request {

    private String request = "changeColorRequest";
    private Date date;
    private String username;

    public changeColorRequest() {

    }

    public changeColorRequest(String username) {
        this.username = username;
    }

    public String getRequest() {
        return this.request;
    }

    public Date getDate() {
        return this.date;
    }

}
