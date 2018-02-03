package requests;

public class StartGameRequest extends Request {

  private RequestType request = RequestType.START_GAME;

  public StartGameRequest() {
  }

  public StartGameRequest(int lobbyId) {
    super(lobbyId);
  }

  public RequestType getType() {
    return this.request;
  }

}
