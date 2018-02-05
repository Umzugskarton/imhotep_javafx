package requests.lobby;

import requests.Request;
import requests.RequestType;

public class LeaveLobbyRequest extends Request {

    private RequestType request = RequestType.LEAVE_LOBBY;

    public LeaveLobbyRequest(int lobbyId) {
        super(lobbyId);
    }

    public RequestType getType() {
        return this.request;
    }
}
