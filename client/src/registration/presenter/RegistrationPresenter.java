package registration.presenter;

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
