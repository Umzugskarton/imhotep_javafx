package requests.start.registration;

import requests.IRequest;
import requests.RequestType;

public class RegisterRequest implements IRequest {

  private RequestType request = RequestType.REGISTER;
  private String username;
  private String pw;
  private String email;

  public RegisterRequest(String username, String password, String email) {
    this.username = username;
    this.pw = password;
    this.email = email;
  }

  public RequestType getType() {
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
