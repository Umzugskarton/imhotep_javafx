package events.main;

public class JoinEvent extends MainEvent {
    private String event = "join";
    private boolean success;

    public JoinEvent() {

    }

    public JoinEvent(String msg, boolean success) {
        this.success = success;
    }

    public String getEvent() {
        return event;
    }

    public boolean getSuccess() {
        return this.success;
    }
}
