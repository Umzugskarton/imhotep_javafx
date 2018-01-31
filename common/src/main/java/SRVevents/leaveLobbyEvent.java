package SRVevents;

public class leaveLobbyEvent implements Event {

  private String event = "leaveLobby";
  private String msg;
  private boolean success;

  public leaveLobbyEvent(boolean success) {
    this.success = success;
  }

  public String getMsg() {
    return this.msg;
  }

  public boolean getSuccess() {
    return this.success;
  }
}
