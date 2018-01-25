package CLTrequests;

import java.util.Date;

public class logoutRequest extends Request {

  private String request = "logout";

  public logoutRequest() {

  }

  public logoutRequest(int lobbyId){
    super(lobbyId);
  }

  public String getType() {
    return this.request;
  }

}
