package events.app.lobby.join;

import data.lobby.CommonLobby;
import events.Event;

public class JoinLobbySuccessfulEvent extends Event {

    CommonLobby lobby;

    public JoinLobbySuccessfulEvent(CommonLobby commonLobby){
        super();
        this.lobby = commonLobby;
        setLobbyId(commonLobby.getLobbyId());
    }

    public CommonLobby getLobby() {
        return this.lobby;
    }
}
