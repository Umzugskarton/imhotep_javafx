package events.main.login;

import events.Event;

public class LoginFailedEvent extends Event {

    private final String reason;

    public LoginFailedEvent(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}
