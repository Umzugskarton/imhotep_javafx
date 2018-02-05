package events.start.login;

import events.Event;

public class LoginEvent extends Event {

  private boolean success;

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public boolean getSuccess() {
    return this.success;
  }
}
