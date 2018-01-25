package CLTrequests;

import java.util.Date;

public class userlistRequest implements Request {

  private String request = "userList";
  private Date date;

  public userlistRequest() {
    this.date = new Date();
  }

  public String getType() {
    return this.request;
  }

  public Date getDate() {
    return this.date;
  }
}
