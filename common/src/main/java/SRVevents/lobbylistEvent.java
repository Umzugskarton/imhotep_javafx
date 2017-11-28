package SRVevents;

import commonLobby.CLTLobby;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Date;

public class lobbylistEvent implements Event {
    private String event = "lobbylist";
    private Date date;
    private ArrayList<CLTLobby> lobbies = new ArrayList<>();

    public lobbylistEvent() {
        this.date = new Date();
    }

    public String getEvent() {
        return this.event;
    }

    public void setLobbies(ArrayList<CLTLobby> lobbies) {
        this.lobbies.addAll(lobbies);
    }

    public ArrayList<CLTLobby> getLobbies() {
        return this.lobbies;
    }

    public Date getDate() {
        return this.date;
    }

}
