package GameEvents;


import GameObjects.Boat;
import SRVevents.Event;

import java.util.Date;

public class gameInfoEvent implements Event {
    private String event = "gameInfo";
    private Boat[] cboats;
    private int round;
    private boolean[] storages;
    private String[] order;
    private Date date;

    public gameInfoEvent() {
        this.date = new Date();
    }

    public void setCboats(Boat[] cboats) {
        this.cboats = cboats;
    }

    public boolean[] getStorages() {
        return storages;
    }

    public int getRound() {
        return round;
    }

    public Boat[] getCboats() {
        return cboats;
    }

    public String getEvent() {
        return event;
    }

    public String[] getOrder() {
        return order;
    }

    public void setOrder(String[] order) {
        this.order = order;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public void setStorages(boolean[] storages) {
        this.storages = storages;
    }

    public Date getDate() {
        return this.date;
    }
}
