package registration.presenter;

import json.ClientCommands;
import main.SceneController;
import org.json.simple.JSONObject;
import registration.view.*;

public class RegistrationPresenter {

  private RegistrationView view;
  private SceneController sceneController;

  public RegistrationPresenter(RegistrationView view, SceneController sceneController) {
    this.sceneController = sceneController;
    this.view = view;
    view.setRegistrationPresenter(this);
  }

  public void sendRegisterRequest(String username, String password1, String password2, String email) {
    if (this.validate(password1, password2, username, email)) {
      JSONObject registerCommand = ClientCommands.registerCommand(username, password1, email);
      this.sceneController.getClientSocket().send(registerCommand);
    } else {
      this.view.updateStatusLabel("Ungültige Eingaben.");
    }
  }

  public void processRegisterResponse(String message) {
    this.view.updateStatusLabel(message);
  }

  public void toLoginScene() {
    sceneController.toLoginScene();
  }

  public RegistrationView getRegistrationView() {
    return this.view;
  }

  private boolean validate(String password1, String password2, String username, String email) {
    if (!password1.isEmpty() && !password2.isEmpty() && !username.isEmpty() && !email.isEmpty()
        && password1.equals(password2) && password1.length() >= 8) {
      return true;
    }
    return false;
  }
}