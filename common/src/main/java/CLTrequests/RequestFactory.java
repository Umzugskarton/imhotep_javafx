package CLTrequests;

import java.util.HashMap;


public class RequestFactory {
    private HashMap<String, Request> Dict = new HashMap<>();

    public RequestFactory() {
        Dict.put("register", new registerRequest());
        Dict.put("login", new loginRequest());
        Dict.put("logout", new logoutRequest());
        Dict.put("userlist", new userlistRequest());
        Dict.put("join", new joinRequest());
        Dict.put("lobbylist", new lobbylistRequest());
        Dict.put("create", new createRequest());
        Dict.put("whisper", new whisperRequest());
        Dict.put("chat", new chatRequest());
    }

    public Request getRequest(String j) {
        return Dict.get(j);
    }
}
