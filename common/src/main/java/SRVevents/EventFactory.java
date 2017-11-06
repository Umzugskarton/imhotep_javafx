package SRVevents;

import java.util.HashMap;

public class EventFactory {
    private HashMap<String, Event> Dict = new HashMap<>();

    public EventFactory(){
        Dict.put("register", new registerEvent());
        Dict.put("login", new loginEvent());
        Dict.put("logout", new logoutEvent());
        Dict.put("userlist", new userListEvent());
        Dict.put("whisper", new whisperEvent());
        Dict.put("chat", new chatEvent());
        Dict.put("chatInfo", new chatInfoEvent());
        Dict.put("lobbylist", new lobbylistEvent());
        Dict.put("lobbyInfo", new lobbyInfoEvent());
        Dict.put("join", new joinEvent());
        Dict.put("create", new createEvent());
        Dict.put("changeCredential", new changeCredentialEvent());
    }

    public Event getEvent(String command){
           return Dict.get(command);
    }


}
