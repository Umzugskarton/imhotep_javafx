package requests;

public class joinRequest extends Request {

  private String request = "join";
  private String pw;

  public joinRequest(int lobbyId, String password) {
    super(lobbyId);
    this.pw = password;
  }

  public String getType() {
    return this.request;
  }

  public String getPassword() {
    return this.pw;
  }
}
