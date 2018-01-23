package SRVevents;

import commonLobby.CLTLobby;
import commonLobby.LobbyUser;

import java.util.ArrayList;
import java.util.Date;

public class lobbyInfoEvent implements Event {
    private String event = "lobbyInfo";
    private CLTLobby lobby;

    public lobbyInfoEvent() {
    }

    public lobbyInfoEvent(CLTLobby lobby) {
        this.lobby = lobby;
    }

    public CLTLobby getLobby() {
        return lobby;
    }

}
