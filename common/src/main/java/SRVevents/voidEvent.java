package SRVevents;

import org.json.simple.JSONObject;
import java.util.Date;

public interface voidEvent {
    String event = null;
    Date date = null;
    String msg = null;

    Date getDate();
}
