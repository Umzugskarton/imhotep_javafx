package requests.main;


public class RegisterRequest extends MainRequest {

  private String request = "register";
  private String username;
  private String pw;
  private String email;

  public RegisterRequest() {

  }

  public RegisterRequest(String username, String password, String email) {
    this.username = username;
    this.pw = password;
    this.email = email;
  }

  public String getUsername() {
    return this.username;
  }

  public String getEmail() {
    return this.email;
  }

  public String getPassword() {
    return this.pw;
  }

}
