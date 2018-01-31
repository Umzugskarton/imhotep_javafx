package requests.main;

public class WhisperRequest extends MainRequest {

  String request = "whisper";
  private String to;
  private String msg;

  public WhisperRequest() {

  }

  public WhisperRequest(String to, String msg) {
    this.to = to;
    this.msg = msg;
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
