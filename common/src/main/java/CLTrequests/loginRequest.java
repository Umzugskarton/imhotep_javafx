package CLTrequests;

import java.util.Date;

public class loginRequest implements IRequest {

  private String request = "login";
  private Date date;
  private String username;
  private String pw;

  public loginRequest() {
  }

  public loginRequest(String username, String password) {
    this.username = username;
    this.pw = password;
    this.date = new Date();
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

  public Date getDate() {
    return this.date;
  }
}
