package CLTrequests;

import java.util.Date;

public class startGameRequest implements Request {
  private String request = "startGame";
  private Date date;


  public startGameRequest() {
    this.date = new Date();
  }


  public String getType() {
    return this.request;
  }

}
