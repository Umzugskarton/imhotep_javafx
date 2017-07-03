package registration.presenter;

import json.ClientCommands;
import main.SceneController;
import org.json.simple.JSONObject;
import registration.view.RegistrationView;

public class RegistrationPresenter {

  private RegistrationView view;
  private SceneController sceneController;

  public RegistrationPresenter(RegistrationView view, SceneController sceneController) {
    this.sceneController = sceneController;
    this.view = view;
    view.setRegistrationPresenter(this);
  }

  public void sendRegisterRequest(String username, String password1, String password2,
      String email) {
    if (this.validate(password1, password2, username, email)) {
      this.view.updateStatusLabel("");
      JSONObject registerCommand = ClientCommands.registerCommand(username, password1, email);
      this.sceneController.getClientSocket().send(registerCommand);
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

  public SceneController getSceneController() {
    return this.sceneController;
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
    this.view.updateStatusLabel(msg);
    return false;
  }
}