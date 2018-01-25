package SRVevents;

import java.util.ArrayList;
import java.util.Date;


public class SetReadyEvent implements Event {

    private String event = "setReady";
    private ArrayList<Boolean> readyList;
    private int lobbyId;

    public SetReadyEvent(ArrayList<Boolean> readyList, int lobbyid) {
        this.readyList = readyList;
        this.lobbyId = lobbyid;
    }


    public String getEvent() {
        return this.event;
    }

    public Date getDate() {
        return this.date;
    }

    public String getMsg() { return this.msg; }

    public ArrayList<Boolean> getReady() { return readyList; }


}
