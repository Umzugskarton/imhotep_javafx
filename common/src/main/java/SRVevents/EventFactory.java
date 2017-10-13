package SRVevents;

import org.json.simple.JSONObject;
import java.util.HashMap;

public class EventFactory {
    private HashMap<String, voidEvent> Dict = new HashMap<>();

    public EventFactory(){
        Dict.put("register", new registerEvent());
        Dict.put("login", new loginEvent());
        Dict.put("logout", new logoutEvent());
        Dict.put("userlist", new userListEvent());
        Dict.put("whisper", new whisperEvent());
        Dict.put("chat", new chatEvent());
        Dict.put("chatInfo", new chatInfoEvent());
    }

    public voidEvent getEvent(String command){
           return Dict.get(command);
    }


}
