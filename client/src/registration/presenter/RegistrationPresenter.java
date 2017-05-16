package registration.presenter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import json.ClientCommands;
import main.SceneController;
import org.json.simple.JSONObject;
import registration.view.*;

public class RegistrationPresenter {

  private RegistrationView view;
  private SceneController sc;
  public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
      Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

  public RegistrationPresenter(RegistrationView view, SceneController sc) {
    this.sc = sc;
    this.view = view;
    view.setRegistrationPresenter(this);
  }

  public void register(String username, String password1, String password2, String email) {
    if(validate(password1, password2, username, email)) {
      JSONObject registerCommand = ClientCommands.registerCommand(username, password1, email);
      this.sc.getClientSocket().send(registerCommand);
    } else {
      this.setResult("Ungültige Eingaben.");
    }
  }

  public void toLoginScene() {
    sc.toLoginScene();
  }

  public RegistrationView getRegistrationView() {
    return this.view;
  }

  public void setResult(String message) {
    this.view.updateStatusLabel(message);
  }

  public boolean validate(String password1, String password2, String name, String email) {
    Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
    if (!password1.isEmpty() && !password2.isEmpty() && !name.isEmpty() && !email.isEmpty() && password1.equals(password2) && matcher.find() && password1.length() > 8) {
      return true;
    }
    return false;
  }

}