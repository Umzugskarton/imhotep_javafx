package requests.main;

public class LoginRequest extends MainRequest {

  private String request = "login";
  private String username;
  private String pw;

  public LoginRequest() {
  }

  public LoginRequest(String username, String password) {
    this.username = username;
    this.pw = password;
  }

  public String getUsername() {
    return this.username;
  }

  public String getPassword() {
    return this.pw;
  }


  @Override
  public void doIt(){

  }

}
