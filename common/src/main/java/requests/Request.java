package requests;

import java.util.Date;

public abstract class Request implements IRequest {

  private int lobbyId;
  private Date date;

  public Request() {
    this.date = new Date();
  }

  public int getLobbyId() {
    return lobbyId;
  }

  public void setLobbyId(int lobbyId) {
    this.lobbyId = lobbyId;
  }
}
