package requests;


public class lobbylistRequest implements IRequest {

  private String request = "lobbylist";

  public lobbylistRequest() {

  }

  public String getType() {
    return this.request;
  }


}
