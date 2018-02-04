package requests.lobby;

import requests.Request;
import requests.RequestType;

public class StartGameRequest extends Request {

  private RequestType request = RequestType.START_GAME;


  public StartGameRequest(int lobbyId) {
    super(lobbyId);
  }

  public RequestType getType() {
    return this.request;
  }

}
