package requests;


public class chatRequest implements IRequest {

  private String request = "chat";
  private Integer lobbyId;
  private String msg;

  public chatRequest() {
  }

  public chatRequest(String msg) {
    this.msg = msg;
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
}
