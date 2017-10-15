package SRVevents;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created on 13.10.2017.
 */
public class lobbyInfoEvent implements Event {
    private int lobbyId;
    private String[] users;
    private boolean host;
    private boolean[] ready;
    private ArrayList<String> colors;

    public lobbyInfoEvent(){}

    public lobbyInfoEvent(int lobbyId, String[] users, boolean host, boolean[] ready, ArrayList<String> colors){
        this.lobbyId= lobbyId;
        this.users = users;
        this.host = host;
        this.ready= ready;
        this.colors= colors;
    }

    public boolean isHost() {
        return host;
    }

    public boolean[] getReady() {
        return ready;
    }

    public ArrayList<String> getColors() {
        return colors;
    }

    public void setColors(ArrayList<String> colors) {
        this.colors = colors;
    }

    public void setUsers(String[] users) {
        this.users = users;
    }

    public void setLobbyId(int lobbyId) {
        this.lobbyId = lobbyId;
    }

    public String[] getUsers() {
        return users;
    }

    public int getLobbyId() {
        return lobbyId;
    }

    public void setHost(boolean host) {
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
