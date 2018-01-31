package SRVevents;

public class chatInfoEvent implements Event {

  private String event = "chatInfo";
  private String msg;

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public String getMsg() {
    return this.msg;
  }
}
