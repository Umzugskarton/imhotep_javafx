package socket;

import java.util.ArrayList;
import json.ServerCommands;
import org.json.simple.JSONObject;
import user.*;

public class ClientAPI {

  private UserManager userManager;
  private ArrayList<User> userList; // Überlegung: Als static im Clientlistener?

  public ClientAPI() {
    this.userManager = new UserManager();
    this.userList = new ArrayList<>();
  }

  /**
   * Wenn Client eine Login-Anfrage sendet, werden die Logindaten
   * via validateLogin überprüft. Wenn die Daten korrekt sind, wird
   * eine Erfolgsnachricht an den Client gesendet.
   * Wenn Logindaten inkorrekt sind, wird eine Fehlermeldung an den
   * Client gesendet.
   *
   * @param request JSON-Objekt, das User-Daten für Login enthält
   * @return JSON-Objekt, das entweder Erfolg oder Misserfolg als Nachricht enthält
   */
  public JSONObject login(JSONObject request) {
    JSONObject response = new JSONObject();

    if (request.containsKey("username") && request.containsKey("password")) {
      String username = (String) request.get("username");
      String password = (String) request.get("password");

      boolean isLoginValid = this.userManager.validateLogin(username, password);

      if (isLoginValid) {
        response = ServerCommands.loginCommand("Login erfolgreich!", true);
      } else {
        response = ServerCommands
            .loginCommand("Login fehlgeschlagen: Username oder Passwort inkorrekt", false);
      }
    } else {
      response = ServerCommands.loginCommand("Login fehlgeschlagen: Ungültige Anfrage", false);
    }
    return response;
  }

  /**
   * Wenn Client eine Registrierungs-Anfrage sendet, versucht der UserManager
   * einen neuen Benutzer anzulegen. Wenn die Erstellung erfolgreich war, wird
   * eine Erfolgsnachricht an den Client gesendet.
   * Wenn die Erstellung nicht erfolgreich war, wird eine Fehlermeldung an den
   * Client gesendet.
   *
   * @param request JSON-Objekt, das User-Daten für Registrierung enthält
   * @return JSON-Objekt, das entweder Erfolg oder Misserfolg als Nachricht enthält
   */
  public JSONObject register(JSONObject request) {
    JSONObject response;

    if (request.containsKey("username") && request.containsKey("password") && request
        .containsKey("email")) {
      String username = (String) request.get("username");
      String password = (String) request.get("password");
      String email = (String) request.get("email");

      boolean createUser = this.userManager.createUser(username, password, email);

      if (createUser) {
        response = ServerCommands.registerCommand("Registrierung erfolgreich!", true);
      } else {
        response = ServerCommands
            .registerCommand("Registrierung fehlgeschlagen: Username oder E-Mail existiert bereits",
                false);
      }
    } else {
      response = ServerCommands
          .registerCommand("Registrierung fehlgeschlagen: Ungültige Anfrage", false);
    }
    return response;
  }
  /**
   * Ist der Login() erfolgreich, so wird ein Userelement über
   * dern Usernamen initiert und der Userliste hinzugefügt.
   * Der Clientlistener speichert den User.
   *
   * @param username String, der Username des einzuloggenden Users enthält
   * @return user enthält den User der eingeloggt wurde und gibt diesen an den Clientlistener Thread
   */
  public User getUser(String username){
    User user = this.userManager.getUserByUsername(username);
    this.userList.add(this.userList.size(), user);
    return user;
  }

  /**
   * Sobald der Logout ausgeführt wird, wird der auszuloggende
   * User aus der UserList gelöscht
   *
   * @param user User, der den auszuloggenden User enthält
   */
  public void logout(User user){
    this.userList.remove(user);
  }

  /**
   * Erstellt eine Liste der Usernames der eingeloggten User
   * und verpackt dies in eine userlistCommand.
   *
   * @return JSON-Object ServerCommands.userlistCommand(users), JSON-Object, das
   * die Liste der eingeloggten User enthält
   */

  public JSONObject getUserlist(){
    String users = "";
    for(int i = 0 ; i < userList.size(); i++){
      if (i+1 < userList.size()) {
        users += this.userList.get(i).getUsername() + ",";
      }
      else{
        users += this.userList.get(i).getUsername();
      }
    }
    return ServerCommands.userlistCommand(users);
  }
}