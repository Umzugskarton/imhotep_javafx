package CLTrequests;

import java.util.Date;

public class startGameRequest extends Request {
  private String request = "startGame";
  private Date date;


  public startGameRequest(int lobbyId) {
    super(lobbyId);
  }


  public String getType() {
    return this.request;
  }


  public Date getDate() {
    return this.date;
  }
}
