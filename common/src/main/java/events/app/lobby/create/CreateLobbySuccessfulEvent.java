package events.app.lobby.create;

import data.lobby.CommonLobby;
import events.Event;

public class CreateLobbySuccessfulEvent extends Event{
    private final CommonLobby lobby;

    public CreateLobbySuccessfulEvent(CommonLobby commonLobby){
        super();
        this.lobby = commonLobby;
        setLobbyId(commonLobby.getLobbyId());
    }

    public CommonLobby getLobby() {
        return this.lobby;
    }
}
