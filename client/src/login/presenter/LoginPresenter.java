package login.presenter;


import json.ClientCommands;
import login.view.LoginView;
import main.SceneController;
import org.json.simple.JSONObject;

/**
 * Created by mircoskrzipczyk, annkristinklopp on 09.05.17.
 */
public class LoginPresenter {

  private LoginView view;
  private SceneController sc;

  public LoginPresenter(LoginView view, SceneController sc) {
    this.view = view;
    this.sc = sc;
    view.setLoginPresenter(this);
  }

  public void login(String username, String password){
    if(this.validate(username, password)) {
       JSONObject loginCommand = ClientCommands.loginCommand(username, password);
      this.sc.getClientSocket().send(loginCommand);
    } else {
      this.view.updateStatusLabel("Benutzername und Passwort dürfen nicht leer sein");
    }
  }

  public void toRegisterScene(){
      sc.toRegistrationScene();
    }

  public LoginView getLoginView() { return this.view; }

  private boolean validate(String username, String password) {
    if (!password.isEmpty() && !username.isEmpty()) {
      return true;
    }
    return false;
  }
}
