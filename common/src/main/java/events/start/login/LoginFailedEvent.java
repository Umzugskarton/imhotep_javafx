package events.start.login;

import events.Event;
import events.EventReason;

public class LoginFailedEvent extends Event {

  public LoginFailedEvent(EventReason reason) {
    this.setReason(reason);
  }
}
