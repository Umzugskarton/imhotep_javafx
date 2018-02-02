package requests;

public class SetReadyRequest extends Request {

  private String request = "setReady";

  public SetReadyRequest() {
  }

  public SetReadyRequest(int lobbyId) {
    super(lobbyId);
  }

  public String getType() {
    return this.request;
  }

}
