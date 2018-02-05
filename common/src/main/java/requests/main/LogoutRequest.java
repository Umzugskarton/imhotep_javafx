package requests.main;

import requests.Request;
import requests.RequestType;

public class LogoutRequest extends Request {

  private RequestType request = RequestType.LOGOUT;

  public LogoutRequest() {
  }

  public LogoutRequest(int lobbyId) {
    super(lobbyId);
  }

  @Override
  public RequestType getType() {
    return this.request;
  }

}
