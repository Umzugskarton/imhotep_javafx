package events;

import java.io.Serializable;
import java.util.Date;

public class Event implements IEvent, Serializable {
    private final String refactoryClass = this.getClass().getName();
    private final Date date;

    public Event(){
        this.date = new Date();
    }

    public Date getDate() {
        return date;
    }
}
