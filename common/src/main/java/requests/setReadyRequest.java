package requests;

public class setReadyRequest extends Request {

  private String request = "setReady";

  public setReadyRequest() {
  }

  public setReadyRequest(int lobbyId) {
    super(lobbyId);
  }

  public String getType() {
    return this.request;
  }

}
