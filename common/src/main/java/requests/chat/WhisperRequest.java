package requests.chat;

import requests.IRequest;
import requests.Request;
import requests.RequestType;

public class WhisperRequest extends Request {

  RequestType request = RequestType.WHISPER;
  private String to;
  private String msg;

  public WhisperRequest(String to, String msg) {
    this.to = to;
    this.msg = msg;
    setLobbyId(-1);
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
