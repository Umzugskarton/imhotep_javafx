package events.main;

public class SetReadyEvent extends MainEvent {

    private String event = "setReady";
    private boolean[] ready;

    public SetReadyEvent() {

    }

    public SetReadyEvent(boolean[] ready) {
        this.ready = ready;
    }

    public String getEvent() {
        return this.event;
    }

    public boolean[] getReady() { return this.ready; }


}
