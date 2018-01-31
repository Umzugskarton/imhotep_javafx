package SRVevents;

public class joinEvent implements Event {

  private String event = "join";
  private String msg;
  private boolean success;

  public joinEvent(String msg, boolean success) {
    this.msg = msg;
    this.success = success;
  }

  public String getEvent() {
    return event;
  }

  public String getMsg() {
    return this.msg;
  }

  public boolean getSuccess() {
    return this.success;
  }
}
