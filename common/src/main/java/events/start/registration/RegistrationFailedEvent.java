package events.start.registration;

import events.Event;
import events.EventReason;

public class RegistrationFailedEvent extends Event {
    public RegistrationFailedEvent(EventReason reason) {
        this.setReason(reason);
    }
}
