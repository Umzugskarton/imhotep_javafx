package requests;

public class LogoutRequest extends Request {

  private String request = "logout";

  public LogoutRequest() {
  }

  public LogoutRequest(int lobbyId) {
    super(lobbyId);
  }

  public String getType() {
    return this.request;
  }

}
