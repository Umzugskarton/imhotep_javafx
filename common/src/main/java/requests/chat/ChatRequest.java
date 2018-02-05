package requests.chat;


import requests.IRequest;
import requests.Request;
import requests.RequestType;

public class ChatRequest extends Request {

  private RequestType request = RequestType.CHAT;
  private String msg;

  public ChatRequest(String msg) {
    this.msg = msg;
    setLobbyId(-1);
  }

  public String getMsg() {
    return this.msg;
  }

  @Override
  public RequestType getType() {
    return this.request;
  }
}
