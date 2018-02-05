package requests.lobby;

import requests.IRequest;
import requests.RequestType;

public class LobbylistRequest implements IRequest {

    private RequestType request = RequestType.LOBBYLIST;

    public RequestType getType() {
        return this.request;
    }
}
