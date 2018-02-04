package requests.lobby;

import requests.Request;
import requests.RequestType;

public class JoinRequest extends Request {

  private RequestType request = RequestType.JOIN;
  private String pw;

  public JoinRequest(int lobbyId, String password) {
    super(lobbyId);
    this.pw = password;
  }

  public RequestType getType() {
    return this.request;
  }

  public String getPassword() {
    return this.pw;
  }
}
