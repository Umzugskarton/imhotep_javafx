package GameEvents;

import SRVevents.Event;

/**
 * Created by Slothan on 31.01.2018.
 */
public class ChooseCardEvent implements Event {
    private String event = "ChooseCard";
    private int playerId;

    public ChooseCardEvent(int playerId) {
        this.playerId = playerId;
    }
}
