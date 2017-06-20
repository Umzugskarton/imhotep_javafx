package login.presenter;


import json.ClientCommands;
import login.view.LoginView;
import main.SceneController;
import org.json.simple.JSONObject;

public class LoginPresenter {

  private LoginView view;
  private SceneController sceneController;

  public LoginPresenter(LoginView view, SceneController sc) {
    this.view = view;
    this.sceneController = sc;
    view.setLoginPresenter(this);
  }

  public void sendLoginRequest(String username, String password) {
    if (this.validate(username, password)) {
      JSONObject loginCommand = ClientCommands.loginCommand(username, password);
      this.sceneController.getClientSocket().send(loginCommand);
      this.sceneController.setUsername(username);
    } else {
      this.view.updateStatusLabel("Benutzername und Passwort dürfen nicht leer sein");
    }
  }

  public void processLoginResponse(boolean loginSuccessful, String message) {
    if(loginSuccessful) {
      this.toMainmenuScene();
    } else {
      this.sceneController.setUsername(null);
      this.view.updateStatusLabel(message);
    }
  }

  public void toRegisterScene() {
    sceneController.toRegistrationScene();
  }

  public void toMainmenuScene() { sceneController.toMainmenuScene(); }

  public LoginView getLoginView() {
    return this.view;
  }

  private boolean validate(String username, String password) {
    if (!password.isEmpty() && !username.isEmpty()) {
      return true;
    }
    return false;
  }
}
