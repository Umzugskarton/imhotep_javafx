package ui.start.login;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import connection.Connection;
import events.start.login.LoginFailedEvent;
import mvp.presenter.Presenter;
import requests.start.login.LoginRequest;

public class LoginPresenter extends Presenter<ILoginView> {

  private final Connection connection;

  public LoginPresenter(ILoginView view, EventBus eventBus, Connection connection) {
    super(view, eventBus);
    this.connection = connection;
    getEventBus().register(this);
  }

  public void sendLoginRequest(String username, String password) {
    if (this.validate(username, password)) {
      getView().showLoginFailed("");
      LoginRequest loginCommand = new LoginRequest(username, password);
      connection.send(loginCommand);
    } else {
      getView().clearForm();
      getView().showLoginFailed("Falsche Anmeldung");
    }
  }

  @Subscribe
  public void onLoginFailedEvent(LoginFailedEvent event) {
    getView().showLoginFailed(event.getReason().toString());
  }

  private boolean validate(String username, String password) {
    return !password.isEmpty() && !username.isEmpty();
  }
}