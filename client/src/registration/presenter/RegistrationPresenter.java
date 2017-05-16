package registration.presenter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

  public void register(String username, String password1, String password2, String email) {
    if(validate(password1, password2, username, email)) {
      JSONObject registerCommand = ClientCommands.registerCommand(username, password1, email);
      this.sceneController.getClientSocket().send(registerCommand);
    } else {
      this.setResult("Ungültige Eingaben.");
    }
  }

  public void toLoginScene() {
    sceneController.toLoginScene();
  }

  public RegistrationView getRegistrationView() {
    return this.view;
  }

  public void setResult(String message) {
    this.view.updateStatusLabel(message);
  }

  public boolean validate(String password1, String password2, String name, String email) {
    if (!password1.isEmpty() && !password2.isEmpty() && !name.isEmpty() && !email.isEmpty() && password1.equals(password2) && password1.length() > 8) {
      return true;
    }
    return false;
  }

}