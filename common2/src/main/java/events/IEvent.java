package events;

import java.util.Date;

public interface IEvent  {
    String event = null;
    Date date = null;

    Date getDate();
}
