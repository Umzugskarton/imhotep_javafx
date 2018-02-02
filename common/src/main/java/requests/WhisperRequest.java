package requests;

public class WhisperRequest implements IRequest {

  String request = "whisper";
  private String to;
  private String msg;

  public WhisperRequest(String to, String msg) {
    this.to = to;
    this.msg = msg;
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

}
