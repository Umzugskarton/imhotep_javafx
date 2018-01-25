package CLTrequests;

import java.util.Date;

public class logoutRequest implements IRequest {

  private String request = "logout";
  private Date date;

  public logoutRequest() {
    this.date = new Date();
  }

  public String getType() {
    return this.request;
  }

  public Date getDate() {
    return this.date;
  }
}
