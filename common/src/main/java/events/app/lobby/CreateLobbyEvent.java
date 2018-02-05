package events.app.lobby;

import events.Event;

public class CreateLobbyEvent extends Event {

    private String msg;
    private boolean success;

    public CreateLobbyEvent(boolean success, int lobbyID, String msg) {
        this.success = success;
        this.lobbyId = lobbyID;
        this.msg = msg;
    }

    public boolean getSuccess() {
        return this.success;
    }

    public String getMsg() {
        return msg;
    }
}
