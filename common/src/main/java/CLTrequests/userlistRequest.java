package CLTrequests;

import java.util.Date;

public class userlistRequest implements IRequest {

  private String request = "userList";
  private Date date;

  public userlistRequest() {
    this.date = new Date();
  }

  public String getType() {
    return this.request;
  }

}
