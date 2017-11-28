package CLTrequests;

import java.util.Date;

public class chatRequest implements Request {

  private String request = "chat";
  private Integer lobbyId;
  private Date date;
  private String msg;

  public chatRequest() {
  }

  public chatRequest(String msg) {
    this.msg = msg;
    this.date = new Date();
    lobbyId = null;
  }

  public Integer getLobbyId() {
    return lobbyId;
  }

  public void setLobbyId(Integer lobbyId) {
    this.lobbyId = lobbyId;
  }

  public String getMsg() {
    return this.msg;
  }

  public String getType() {
    return this.request;
  }

  public Date getDate() {
    return this.date;
  }
}
