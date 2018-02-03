package requests;

public class ChangeColorRequest extends Request {

  private RequestType request = RequestType.CHANGE_COLOR;

  public ChangeColorRequest() {
  }

  public ChangeColorRequest(int lobbyId) {
    super(lobbyId);
  }

  public RequestType getType() {
    return this.request;
  }

}
