package events.app.lobby;

import data.lobby.CommonLobby;
import events.Event;

public class LobbyInfoEvent extends Event {

    private CommonLobby lobby;

    public LobbyInfoEvent() {
    }

    public LobbyInfoEvent(CommonLobby lobby) {
        this.lobby = lobby;
    }


    public CommonLobby getLobby() {
        return lobby;
    }

}
