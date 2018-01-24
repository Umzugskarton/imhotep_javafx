package CLTrequests;

import java.util.Date;


public class changeColorRequest extends Request {

  private String request = "changeColor";

  public changeColorRequest(int lobbyId) {
    super(lobbyId);
  }


  public String getType() {
    return this.request;
  }
}
