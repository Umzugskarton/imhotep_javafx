package CLTrequests;

import java.util.Date;

public class whisperRequest implements Request {

  String request = "whisper";
  private Date date;
  private String to;
  private String msg;

  public whisperRequest() {

  }

  public whisperRequest(String to, String msg) {
    this.to = to;
    this.msg = msg;
    this.date = new Date();
  }

  public String getType() {
    return this.request;
  }

  public String getTo() {
    return this.to;
  }

  public String getRecipient() {
    return this.to;
  }

  public String getMsg() {
    return this.msg;
  }

  public Date getDate() {
    return this.date;
  }
}
