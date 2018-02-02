package requests;

public class StartGameRequest extends Request {

  private String request = "startGame";

  public StartGameRequest() {
  }

  public StartGameRequest(int lobbyId) {
    super(lobbyId);
  }

  public String getType() {
    return this.request;
  }

}
