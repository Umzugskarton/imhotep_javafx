package events.app.lobby;

import data.lobby.CommonLobby;
import events.Event;

import java.util.ArrayList;

public class LobbyListEvent extends Event {

    private ArrayList<CommonLobby> lobbies = new ArrayList<>();

    public void setLobbies(ArrayList<CommonLobby> lobbies) {
        this.lobbies.addAll(lobbies);
    }

    public ArrayList<CommonLobby> getLobbies() {
        return this.lobbies;
    }
}
