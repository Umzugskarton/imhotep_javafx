package requests;

public class registerRequest implements IRequest {

  private String request = "register";
  private String username;
  private String pw;
  private String email;

  public registerRequest(String username, String password, String email) {
    this.username = username;
    this.pw = password;
    this.email = email;
  }

  public String getType() {
    return this.request;
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
