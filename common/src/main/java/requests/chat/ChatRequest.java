package requests.chat;


import requests.IRequest;
import requests.RequestType;

public class ChatRequest implements IRequest {

  private RequestType request = RequestType.CHAT;
  private int lobbyId;
  private String msg;

  public ChatRequest(String msg) {
    this.msg = msg;
    lobbyId = -1;
  }

  public int getLobbyId() {
    return lobbyId;
  }

  public void setLobbyId(int lobbyId) {
    this.lobbyId = lobbyId;
  }

  public String getMsg() {
    return this.msg;
  }

  @Override
  public RequestType getType() {
    return this.request;
  }
}
