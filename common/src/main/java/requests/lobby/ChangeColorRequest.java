package requests.lobby;

import requests.Request;
import requests.RequestType;

public class ChangeColorRequest extends Request {

  private RequestType request = RequestType.CHANGE_COLOR;

  public ChangeColorRequest(int lobbyId) {
    super(lobbyId);
  }

  public RequestType getType() {
    return this.request;
  }

}
