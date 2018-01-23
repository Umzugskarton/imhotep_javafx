package GameEvents;

import SRVevents.Event;

public class OutOfStonesError implements Event{
    private String type = "OutOfStonesError";
    private int playerId;
    public OutOfStonesError(int playerId){
     this.playerId = playerId;
    }
}
