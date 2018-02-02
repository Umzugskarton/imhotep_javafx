package requests;

public class LoginRequest implements IRequest {

  private String request = "login";
  private String username;
  private String pw;

  public LoginRequest(String username, String password) {
    this.username = username;
    this.pw = password;
  }

  public String getType() {
    return this.request;
  }

  public String getUsername() {
    return this.username;
  }

  public String getPassword() {
    return this.pw;
  }


}
