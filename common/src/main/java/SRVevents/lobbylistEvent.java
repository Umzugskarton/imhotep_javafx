package SRVevents;

import commonLobby.CLTLobby;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created on 13.10.2017.
 */
public class lobbylistEvent implements Event {
    private String event = "lobbylist";
    private Date date;
    private ObservableList<CLTLobby> lobbies = FXCollections.observableArrayList();

    public lobbylistEvent(){
        this.date = new Date();
    }

    public String getEvent(){
        return this.event;
    }

    public void setLobbies(ArrayList<CLTLobby> lobbies){
        this.lobbies.addAll(lobbies);
    }

    public ObservableList<CLTLobby> getLobbies(){
        return this.lobbies;
    }

    public Date getDate(){
        return this.date;
    }

}
