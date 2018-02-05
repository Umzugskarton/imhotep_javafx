package events.start.login;

import data.user.User;
import events.Event;

public class LoginSuccessfulEvent extends Event {
    private User user;

    public LoginSuccessfulEvent(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
