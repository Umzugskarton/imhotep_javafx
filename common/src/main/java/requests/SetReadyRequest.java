package requests;

public class SetReadyRequest extends Request {

  private RequestType request = RequestType.SET_READY;


  public SetReadyRequest(int lobbyId) {
    super(lobbyId);
  }

  public RequestType getType() {
    return this.request;
  }

}
