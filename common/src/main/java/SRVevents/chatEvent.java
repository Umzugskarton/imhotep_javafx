package SRVevents;

import java.util.Date;

public class chatEvent implements Event {

  private String event = "chat";
  private Date date;
  private String user;
  private Integer lobbyId;
  private String msg;

  public chatEvent() {
    lobbyId = null;
    this.date = new Date();
  }

  public Integer getLobbyId() {
    return lobbyId;
  }

  public void setLobbyId(Integer lobbyId) {
    this.lobbyId = lobbyId;
  }

  public void setUser(String username) {
    this.user = username;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public String getUser() {
    return this.user;
  }

  public String getMsg() {
    return this.msg;
  }

  public Date getDate() {
    return this.date;
  }

}
