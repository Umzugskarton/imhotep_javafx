package events.main;

import data.lobby.Lobby;

public class LobbyInfoEvent extends MainEvent {
    private String event = "lobbyInfo";
    private Lobby lobby;

    public LobbyInfoEvent() {
    }

    public LobbyInfoEvent(Lobby lobby) {
        this.lobby = lobby;
    }


    public Lobby getLobby() {
        return lobby;
    }
}
