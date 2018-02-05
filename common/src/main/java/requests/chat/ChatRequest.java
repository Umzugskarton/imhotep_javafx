package requests.chat;


import requests.IRequest;
import requests.RequestType;

public class ChatRequest implements IRequest {

    private RequestType request = RequestType.CHAT;
    private Integer lobbyId;
    private String msg;

    public ChatRequest(String msg) {
        this.msg = msg;
        lobbyId = null;
    }

    public Integer getLobbyId() {
        return lobbyId;
    }

    public void setLobbyId(Integer lobbyId) {
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
