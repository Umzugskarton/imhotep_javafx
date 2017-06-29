package json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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

  public static JSONObject chatCommand(String user, String msg) {
    JSONObject obj = new JSONObject();

    obj.put("command", "chat");
    obj.put("user", user);
    obj.put("message", msg);

    return obj;
  }

  public static JSONObject whisperCommand(String from, String msg) {
    JSONObject obj = new JSONObject();

    obj.put("command", "whisper");
    obj.put("from", from);
    obj.put("message", msg);

    return obj;
  }

  public static JSONObject userNotFoundError(String username) {
    JSONObject obj = new JSONObject();

    obj.put("command", "chatInfo");
    obj.put("message", "Der Benutzer " + username + " ist momentan nicht online.");

    return obj;
  }


  public static JSONObject userlistCommand(JSONArray users) {
    JSONObject obj = new JSONObject();

    obj.put("command", "userlist");
    obj.put("users", users);

    return obj;
  }
}
