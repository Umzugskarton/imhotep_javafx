package json;

import java.util.ArrayList;
import org.json.simple.JSONObject;

/**
 * Created by tobia on 16.05.2017.
 */
public class ServerCommands {

  public static JSONObject registerCommand(String message, boolean success) {
    JSONObject obj = new JSONObject();

    obj.put("command", "register");
    obj.put("success", success);
    obj.put("message", message);

    return obj;
  }

  public static JSONObject loginCommand(String message, boolean success) {
    JSONObject obj = new JSONObject();

    obj.put("command", "login");
    obj.put("success", success);
    obj.put("message", message);

    return obj;
  }

  public static JSONObject userlistCommand(String users) {
    JSONObject obj = new JSONObject();

    obj.put("command", "userlist");
    obj.put("users",users);

    return obj;
  }
}
