package requests;

public class LeaveLobbyRequest extends Request {

  private String request = "leaveLobby";

  public LeaveLobbyRequest(int lobbyId) {
    super(lobbyId);
  }

  public String getType() {
    return this.request;
  }
}
