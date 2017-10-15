package SRVevents;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created on 13.10.2017.
 */
public class lobbylistEvent implements Event {
    private String request = "lobbylist";
    private Date date;
    private ArrayList<String> lobbies;

    public lobbylistEvent(){

    }

    public lobbylistEvent(ArrayList<String> lobbies){
        this.lobbies = lobbies;
        this.date = new Date();
    }

    public String getRequest(){
        return this.request;
    }

    public ArrayList<String> getMsg(){
        return this.lobbies;
    }

    public Date getDate(){
        return this.date;
    }

}
