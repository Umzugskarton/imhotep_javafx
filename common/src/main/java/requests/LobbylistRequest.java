package requests;

public class LobbylistRequest implements IRequest {

  private RequestType request = RequestType.LOBBYLIST;

  public LobbylistRequest() {
  }

  public RequestType getType() {
    return this.request;
  }
}
