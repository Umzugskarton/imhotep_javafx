package main.events;

/**
 * Created by fabianrieger on 26.07.17.
 */

import org.json.simple.JSONObject;

import java.util.Date;

public interface voidEvent {
     Date date = null;
     String msg = null;

    Date getDate();
    void init(JSONObject j);
}
