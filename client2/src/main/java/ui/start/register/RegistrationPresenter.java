package ui.start.register;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import connection.Connection;
import events.start.registration.RegistrationEvent;
import mvp.presenter.Presenter;
import requests.RegisterRequest;
import ui.app.ShowAppViewEvent;

public class RegistrationPresenter extends Presenter<IRegistrationView> {

  private final Connection connection;

  public RegistrationPresenter(IRegistrationView view, EventBus eventBus, Connection connection) {
    super(view, eventBus);
    this.connection = connection;
    getEventBus().register(this);
  }


  public void sendRegisterRequest(String username, String password1, String password2,
      String email) {
    if (this.validate(password1, password2, username, email)) {
      getView().updateStatusLabel("");
      RegisterRequest registerCommand = new RegisterRequest(username, password1, email);
      this.connection.send(registerCommand);
    }
  }

  @Subscribe
  public void onRegisterEvent(RegistrationEvent e) {
    getView().updateStatusLabel(e.getReason().toString());
    if(e.isSuccess()){
      this.eventBus.post(new ShowAppViewEvent());
    }
  }

  public void processRegisterResponse(String message) {
    getView().updateStatusLabel(message);

  }

  private boolean validate(String password1, String password2, String username, String email) {
    String msg = "";
    if (!password1.isEmpty() && !password2.isEmpty() && !username.isEmpty() && !email.isEmpty()
        && password1.equals(password2) && password1.length() >= 8) {
      return true;
    } else {
      if (password1.isEmpty()) {
        msg += "Bitte ein Passwort eingeben. \n";
      }
      if (password2.isEmpty()) {
        msg += "Bitte das Passwort wiederholen. \n";
      }
      if (username.isEmpty()) {
        msg += "Bitte einen Benutzernamen eingeben. \n";
      }
      if (email.isEmpty()) {
        msg += "Bitte eine E-mail Adresse eingeben. \n";
      }
      if (!password1.equals(password2)) {
        msg += "Die Passwörter stimmen nicht überein. \n";
      }
      if (password1.length() < 8) {
        msg += "Das Passwort muss mindestens 8 Zeichen besitzen. \n";
      }
    }
    getView().updateStatusLabel(msg);
    return false;
  }
}