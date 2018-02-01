package requests;

public class startGameRequest extends Request {

  private String request = "startGame";

  public startGameRequest() {
  }

  public startGameRequest(int lobbyId) {
    super(lobbyId);
  }

  public String getType() {
    return this.request;
  }

}
