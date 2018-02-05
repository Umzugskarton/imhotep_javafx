package requests.lobby;

import requests.Request;
import requests.RequestType;

public class SetReadyRequest extends Request {

  private RequestType request = RequestType.SET_READY;


  public SetReadyRequest(int lobbyId) {
    setLobbyId(lobbyId);
  }

  public RequestType getType() {
    return this.request;
  }

}
