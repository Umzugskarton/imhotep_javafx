package requests;

public class WhisperRequest implements IRequest {

  RequestType request = RequestType.WHISPER;
  private String to;
  private String msg;

  public WhisperRequest(String to, String msg) {
    this.to = to;
    this.msg = msg;
  }

  public RequestType getType() {
    return this.request;
  }

  public String getTo() {
    return this.to;
  }

  public String getMsg() {
    return this.msg;
  }

}
