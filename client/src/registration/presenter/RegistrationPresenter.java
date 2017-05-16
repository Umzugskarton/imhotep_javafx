package registration.presenter;

import javafx.event.ActionEvent;
import json.ClientCommands;
import main.SceneController;
import org.json.simple.JSONObject;
import registration.view.*;
import javafx.scene.control.PasswordField;

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

  public boolean validate (String password1, String password2, String name, String email){
    if(password1 != null && password1 == password2 && name != null && email != null //&& Emailvalidation) {
      return true;
    }
    else{
      return false;
      //Exception
    }
  }
}
