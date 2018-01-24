package CLTrequests;

import java.util.HashMap;

/**
 * Created on 10.10.2017.
 */
public class RequestFactory {
  private HashMap<String, IRequest> Dict = new HashMap<>();

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
    Dict.put("changeCredential", new changeCredentialRequest());
    Dict.put("changeColor", new changeColorRequest());
    Dict.put("setReady", new setReadyRequest());
    Dict.put("startGame", new startGameRequest());
    Dict.put("leaveLobby", new leaveLobbyRequest());
  }

  public IRequest getRequest(String j) {
    return Dict.get(j);
  }
}
