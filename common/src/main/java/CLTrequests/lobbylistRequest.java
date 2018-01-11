package CLTrequests;

import java.util.Date;

public class lobbylistRequest implements Request {

  private String request = "lobbylist";
  private Date date;

  public lobbylistRequest() {
    this.date = new Date();
  }

  public String getType() {
    return this.request;
  }

  public Date getDate() {
    return this.date;
  }

}
