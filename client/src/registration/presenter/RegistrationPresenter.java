package registration.presenter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

  public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
      Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


  public boolean validate(String password1, String password2, String name, String email){
    Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
    if(password1 != null && password1 == password2 && name != null && email != null  && matcher.find()) {

      return true;
    }
    else{
      return false;
      //Exception
    }
  }
}
