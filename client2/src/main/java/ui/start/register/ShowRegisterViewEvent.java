package ui.start.register;

import mvp.view.ShowViewEvent;

public class ShowRegisterViewEvent extends ShowViewEvent {

  private String username;
  private String passwort;

  public ShowRegisterViewEvent() {
  }

  public ShowRegisterViewEvent(String username, String passwort) {
    this.username = username;
    this.passwort = passwort;
  }

  public String getUsername() {
    return username;
  }

  public String getPasswort() {
    return passwort;
  }
}
