package requests;

public class JoinRequest extends Request {

  private String request = "join";
  private String pw;

  public JoinRequest(int lobbyId, String password) {
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
