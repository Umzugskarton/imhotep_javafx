package events.main;

public class UserNotFoundError extends MainEvent {
    private String event = "userNotFoundError";

    public UserNotFoundError() {
    }

    public void setMsg(String msg) {
        msg = "Der User " + msg + " ist momentan nicht eingeloggt.";
    }
}
