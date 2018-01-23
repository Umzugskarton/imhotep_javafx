package events.main;

import data.lobby.Lobby;

import java.util.ArrayList;

public class LobbylistEvent extends MainEvent {
    private String event = "lobbylist";
    private ArrayList<Lobby> lobbies = new ArrayList<>();

    public LobbylistEvent() {
    }

    public String getEvent() {
        return this.event;
    }

    public void setLobbies(ArrayList<Lobby> lobbies) {
        this.lobbies.addAll(lobbies);
    }

    public ArrayList<Lobby> getLobbies() {
        return this.lobbies;
    }
}
