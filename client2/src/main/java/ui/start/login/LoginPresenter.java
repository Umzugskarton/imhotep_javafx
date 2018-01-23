package ui.start.login;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import connection.Connection;
import mvp.presenter.Presenter;

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
      //LoginRequest loginCommand = new LoginRequest(username, password);
      //connection.send(loginCommand);
    } else {
      getView().showLoginFailed("Benutzername und Passwort dürfen nicht leer sein");
    }
  }

  /*

  @Subscribe
  public void onLoginEvent(LoginEvent event){
    if(event.getSuccess()){
      //getEventBus().post(ShowMainEvent);
    } else {
      getView().showLoginFailed(event.getMsg());
    }
  }

  */

  private boolean validate(String username, String password) {
    return !password.isEmpty() && !username.isEmpty();
  }
}