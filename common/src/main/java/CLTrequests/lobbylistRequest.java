package CLTrequests;


public class lobbylistRequest implements Request {

  private String request = "lobbylist";

  public lobbylistRequest() {

  }

  public String getType() {
    return this.request;
  }


}
