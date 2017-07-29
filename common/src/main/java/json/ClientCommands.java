package json;

import org.json.simple.JSONObject;

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

  public static JSONObject chatCommand(String msg) {
    JSONObject obj = new JSONObject();

    obj.put("command", "chat");
    obj.put("message", msg);

    return obj;
  }

  public static JSONObject createCommand(String name, int size, String psw) {
    JSONObject obj = new JSONObject();

    obj.put("command", "create");
    obj.put("name", name);
    obj.put("size", size);
    if (psw!=null){
      obj.put("password", psw);
    }

    return obj;
  }

  public static JSONObject whisperCommand(String to, String msg) {
    JSONObject obj = new JSONObject();

    obj.put("command", "whisper");
    obj.put("to", to);
    obj.put("message", msg);

    return obj;
  }

  public static JSONObject userlistCommand() {
    JSONObject obj = new JSONObject();

    obj.put("command", "userlist");

    return obj;
  }

  public static JSONObject lobbylistCommand() {
    JSONObject obj = new JSONObject();

    obj.put("command", "lobbylist");

    return obj;
  }

  public static JSONObject logoutCommand() {
    JSONObject obj = new JSONObject();

    obj.put("command", "logout");

    return obj;
  }
}
