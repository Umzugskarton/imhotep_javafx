package SRVevents;

import commonLobby.LobbyUser;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created on 13.10.2017.
 */
public class lobbyInfoEvent implements Event {
    private String event = "lobbyInfo";
    private int lobbyId;
    private ArrayList<LobbyUser> users;
    private boolean ishost;
    private String host;
    private boolean[] ready;
    private ArrayList<String> colors;

    public lobbyInfoEvent(){}

    public lobbyInfoEvent(int lobbyId, ArrayList<LobbyUser> users, boolean ishost, String host, boolean[] ready, ArrayList<String> colors){
        this.lobbyId= lobbyId;
        this.users = users;
        this.ishost = ishost;
        this.host = host;
        this.ready= ready;
        this.colors= colors;
    }

    public boolean isHost() {
        return this.ishost;
    }

    public String getHost(){return this.host;}

    public boolean[] getReady() {
        return ready;
    }

    public ArrayList<String> getColors() {
        return colors;
    }

    public void setColors(ArrayList<String> colors) {
        this.colors = colors;
    }

    public void setUsers(ArrayList<LobbyUser> users) {
        this.users = users;
    }

    public void setLobbyId(int lobbyId) {
        this.lobbyId = lobbyId;
    }

    public ArrayList getUsers() {
        return users;
    }

    public String getEvent() {
        return event;
    }

    public int getLobbyId() {
        return lobbyId;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setReady(boolean[] ready) {
        this.ready = ready;
    }


    @Override
    public Date getDate() {
        return null;
    }
}
