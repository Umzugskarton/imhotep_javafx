package requests;

public class LeaveLobbyRequest extends Request {

  private RequestType request = RequestType.LEAVE_LOBBY;

  public LeaveLobbyRequest(int lobbyId) {
    super(lobbyId);
  }

  public RequestType getType() {
    return this.request;
  }
}
