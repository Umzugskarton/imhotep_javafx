package socket;
import java.sql.*;

import org.apache.commons.codec.digest.DigestUtils;

import database.DBController;
import org.json.simple.JSONObject;
import user.User;
import user.UserManager;

public class ClientAPI {
  private UserManager userManager;

  public ClientAPI() {
    this.userManager = new UserManager();
  }

  /**
   * Wenn Client eine Login-Anfrage sendet, werden die Logindaten
   * via validateLogin �berpr�ft. Wenn die Daten korrekt sind, wird
   * eine entsprechende Session an den Client zur�ckgesendet.
   * Wenn Logindaten inkorrekt sind, wird eine Fehlermeldung an den
   * Client gesendet.
   *
   * @return true, wenn Loginvorgang erfolgreich ausgef�hrt wurde
   * @param  json  Json-Object mit Userdaten
   */
  public JSONObject login(JSONObject json) {
    JSONObject obj = new JSONObject();

    if((json.containsKey("username") || json.containsKey("email")) && json.containsKey("password")) {
      String username = (String) json.get("username");
      String password = (String) json.get("password");
      if (this.userManager.validateLogin(username, password)) {
        obj.put("command", "loginSuccess");
        obj.put("message", "Login succeded");

        return obj;
      }
      else{
        obj.put("command", "loginFailed");
        obj.put("message", "Username oder Passwort falsch.");
      }
    }

    obj.put("command", "loginFailed");
    obj.put("message", "Login failed");

    return obj;
  }

  public JSONObject register(JSONObject json) {
      JSONObject obj = new JSONObject();

      if(json.containsKey("username") && json.containsKey("password") && json.containsKey("email")) {
        String username = (String) json.get("username");
        String password = (String) json.get("password");
        String email = (String) json.get("email");

        boolean createUser = this.userManager.createUser(username, password, email);

        if(createUser) {
          obj.put("command", "registerSuccess");
          obj.put("message", "Register success");
        } else {
          obj.put("command", "registerFailed");
          obj.put("message", "Register failed");
        }

        return obj;
      }

      obj.put("command", "registerFailed");
      obj.put("message", "Register failed");

      return obj;
  }
}