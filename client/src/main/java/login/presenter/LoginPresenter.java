package login.presenter;

import general.TextBundle;
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
      this.view.updateStatusLabel("");
      JSONObject loginCommand = ClientCommands.loginCommand(username, password);
      this.sceneController.getClientSocket().send(loginCommand);
    } else {
        this.view.updateStatusLabel(TextBundle.getString("updateStatus"));
    }
  }

  public void processLoginResponse(boolean loginSuccessful, String message) {
    if (loginSuccessful) {
      this.toMainmenuScene();
      sceneController.getMainmenuPresenter().getChatPresenter()
          .addInfoMessage(TextBundle.getString("infoMessage"));
    } else {
      this.view.updateStatusLabel(message);
    }
  }

  public void toRegisterScene() {
    sceneController.toRegistrationScene();
  }

  private void toMainmenuScene() {
    sceneController.toMainmenuScene();
  }

  public LoginView getLoginView() {
    return this.view;
  }

  public SceneController getSceneController() {
    return this.sceneController;
  }

  private boolean validate(String username, String password) {
    return !password.isEmpty() && !username.isEmpty();
  }
}