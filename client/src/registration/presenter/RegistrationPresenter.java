package registration.presenter;

import javafx.event.ActionEvent;
import json.ClientCommands;
import main.SceneController;
import org.json.simple.JSONObject;
import registration.view.RegistrationView;

public class RegistrationPresenter {
  private RegistrationView view;
  private SceneController sc;

  public RegistrationPresenter(RegistrationView view, SceneController sc) {
    this.sc = sc;
    this.view = view;
    view.setRegistrationPresenter(this);
  }

  public void register(String username, String password, String email) {
    JSONObject registerCommand = ClientCommands.registerCommand(username, password, email);
    this.sc.getClientSocket().send(registerCommand);
  }

  public void toLoginScene(){
    sc.toLoginScene();
  }

  public RegistrationView getRegistrationView(){
    return this.view;
  }

  public void setResult(String message) {
    this.view.updateStatusLabel(message);
  }
}

  void register(ActionEvent event) {
    if (view.getPassword().equals(view.getPasswordPass()) && !view.getName().isEmpty() && !view.getEmail().isEmpty()) {
      if (view.getPassword().length() >= 6) {
      } else {
      }
    }
  }