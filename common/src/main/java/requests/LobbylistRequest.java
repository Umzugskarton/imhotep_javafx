package requests;

public class LobbylistRequest implements IRequest {

  private RequestType request = RequestType.LOBBYLIST;

  public RequestType getType() {
    return this.request;
  }
}
