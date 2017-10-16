package SRVevents;

import commonLobby.CLTLobby;
import commonLobby.LobbyUser;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created on 13.10.2017.
 */
public class lobbyInfoEvent implements Event {
    private String event = "lobbyInfo";
    private CLTLobby lobby;
    public lobbyInfoEvent(){}

    public lobbyInfoEvent(CLTLobby lobby){
        this.lobby = lobby;
    }


    public CLTLobby getLobby() {
        return lobby;
    }

    @Override
    public Date getDate() {
        return null;
    }
}
