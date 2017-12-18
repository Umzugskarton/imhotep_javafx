package CLTrequests;

import java.util.Date;

/**
 * Created by Slothan on 17.12.2017.
 */
public class playerActionRequest implements Request{

    public String request = "playerAction";
    private Date date;


    public String getType() {
        return this.request;
    }

    public Date getDate() {
        return this.date;
    }
}
