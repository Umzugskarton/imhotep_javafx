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

  public static JSONObject logoutCommand(boolean success) {
    JSONObject obj = new JSONObject();

    obj.put("command", "login");
    obj.put("success", success);

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

  public  static JSONObject joinCommand(String msg, boolean success){
    JSONObject obj = new JSONObject();

    obj.put("command", "join");
    obj.put("success", success);
    obj.put("message", msg);

    return obj;
  }

  public  static JSONObject createCommand(String msg, boolean success, int id){
    JSONObject obj = new JSONObject();

    obj.put("command", "create");
    obj.put("success", success);
    obj.put("lobbyid", id);
    obj.put("message", msg);

    return obj;
  }

  public static JSONObject lobbylistCommand(JSONArray lobbies){
    JSONObject obj= new JSONObject();

    obj.put("command", "lobbylist");
    obj.put("lobbies", lobbies);

    return obj;
  }

  public static JSONObject userlistCommand(JSONArray users) {
    JSONObject obj = new JSONObject();

    obj.put("command", "userlist");
    obj.put("users", users);

    return obj;
  }
}
