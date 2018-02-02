package requests;


public class LobbylistRequest implements IRequest {

  private String request = "lobbylist";

  public LobbylistRequest() {

  }

  public String getType() {
    return this.request;
  }


}
