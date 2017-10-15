package SRVevents;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created on 13.10.2017.
 */
public class lobbylistEvent implements Event {
    private String event = "lobbylist";
    private Date date;
    private ArrayList<String> lobbies;

    public lobbylistEvent(){

    }

    public lobbylistEvent(ArrayList<String> lobbies){
        this.lobbies = lobbies;
        this.date = new Date();
    }

    public String getEvent(){
        return this.event;
    }

    public ArrayList<String> getLobbies(){
        return this.lobbies;
    }

    public Date getDate(){
        return this.date;
    }

}
