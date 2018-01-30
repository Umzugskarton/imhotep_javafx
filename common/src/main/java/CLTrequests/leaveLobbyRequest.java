package CLTrequests;

public class leaveLobbyRequest extends Request {

  private String request = "leaveLobby";

  public leaveLobbyRequest() {
  }

  public leaveLobbyRequest(int lobbyId) {
    super(lobbyId);
  }

  public String getType() {
    return this.request;
  }
}
