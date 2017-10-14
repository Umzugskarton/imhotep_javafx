package SRVevents;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created on 13.10.2017.
 */
public class lobbyInfoEvent implements voidEvent {
    private int lobbyId;
    private String[] users;
    private boolean host;
    private boolean[] ready;
    private Color[] colors;

    public lobbyInfoEvent(){}

    public lobbyInfoEvent(int lobbyId, String[] users, boolean host, boolean[] ready, Color[] colors){
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

    public Color[] getColors() {
        return colors;
    }

    public void setColors(Color[] colors) {
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
