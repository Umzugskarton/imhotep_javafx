package SRVevents;

public class logoutEvent implements Event {

  private String event = "logout";
  private boolean success;
  private String msg;

  public String getMsg() {
    return this.msg;
  }

  public boolean getSuccess() {
    return this.success;
  }
}
