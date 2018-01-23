package events.main;


public class LogoutEvent extends MainEvent {
    private String event = "logout";
    private boolean success;

    public LogoutEvent() {
    }

    public boolean getSuccess() {
        return this.success;
    }
}
