package SRVevents;

import java.util.Date;

/**
 * Created by Slothan/Dennis Lindt on 09.11.2017.
 */
public class changeColorEvent implements Event {
    private String event = "changeColorEvent";
    private Date date;
    private String msg;



    public Date getDate() {
        return this.date;
    }
}
