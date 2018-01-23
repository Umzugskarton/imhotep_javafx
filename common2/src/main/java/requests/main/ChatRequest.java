package requests.main;

public class ChatRequest extends MainRequest {

  private String request = "chat";
  private Integer lobbyId;
  private String msg;

  public ChatRequest() {
  }

  public ChatRequest(String msg) {
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

}
