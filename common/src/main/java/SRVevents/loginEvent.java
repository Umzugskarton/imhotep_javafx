package SRVevents;

public class loginEvent implements Event {

  private String event = "login";
  private boolean success;
  private String msg;
  private String username;
  private String email;

  public void setUsername(String username) {
    this.username = username;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getEmail() {
    return email;
  }

  public String getUsername() {
    return username;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public String getMsg() {
    return this.msg;
  }

  public boolean getSuccess() {
    return this.success;
  }
}
