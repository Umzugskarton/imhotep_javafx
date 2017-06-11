package json;

import org.json.simple.JSONObject;

/**
 * Created by tobia on 16.05.2017.
 */
public class ClientCommands {

  public static JSONObject registerCommand(String username, String password, String email) {
    JSONObject obj = new JSONObject();

    obj.put("command", "register");
    obj.put("username", username);
    obj.put("password", password);
    obj.put("email", email);

    return obj;
  }

  public static JSONObject loginCommand(String username, String password) {
    JSONObject obj = new JSONObject();

    obj.put("command", "login");
    obj.put("username", username);
    obj.put("password", password);

    return obj;
  }

  public static JSONObject userlistCommand() {
    JSONObject obj = new JSONObject();

    obj.put("command", "userlist");

    return obj;
  }

  public static JSONObject logoutCommand() {
    JSONObject obj = new JSONObject();

    obj.put("command", "logout");

    return obj;
  }
}
