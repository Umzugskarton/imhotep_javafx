package SRVevents;

import java.util.Date;


public class SetReadyEvent implements Event {

    private String event = "setReady";
    private boolean[] readyList;
    private int lobbyId;


    public SetReadyEvent(boolean[] readyList, int lobbyid) {
        /*Übetragen der ready werte funktioniert komischerweise nur so
        anders sind im Client alle Einträge false*/
        for (int i = 0 ; i<readyList.length; i++){
            this.readyList[i] = readyList[i];
        }
        this.lobbyId = lobbyid;
    }

    public int getLobbyId() {
        return lobbyId;
    }

    public String getEvent() {
        return this.event;
    }

    public Date getDate() {
        return this.date;
    }

    public String getMsg() { return this.msg; }

    public boolean[] getReady() { return readyList; }


}
