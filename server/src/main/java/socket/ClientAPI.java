package socket;

import json.ServerCommands;
import org.json.simple.JSONObject;
import user.User;
import user.UserIdentifier;
import user.UserManager;

public class ClientAPI {

  private UserManager userManager;

  public ClientAPI() {
    this.userManager = new UserManager();
  }

  /**
   * Wenn Client eine Login-Anfrage sendet, werden die Logindaten
   * via validateLogin überprüft. Wenn die Daten korrekt sind, wird
   * eine Erfolgsnachricht an den Client gesendet.
   * Wenn Logindaten inkorrekt sind, wird eine Fehlermeldung an den
   * Client gesendet.
   *
   * @param request JSON-Objekt, das User-Daten für Login enthält;
   * @param loggedUsers  JSON-Objekt, Liste eingeloggter User
   * @return JSON-Objekt, das entweder Erfolg oder Misserfolg als Nachricht enthält
   */
  public JSONObject login(JSONObject request, JSONObject loggedUsers) {
    JSONObject response = new JSONObject();
    if (request.containsKey("username") && request.containsKey("password")) {
      String username = (String) request.get("username");
      String password = (String) request.get("password");
      if (loggedUsers.get("users").toString().contains(username)) {
        response = ServerCommands.loginCommand("Login fehlgeschlagen: Bereits eingeloggt!", false);
      } else{
        boolean isLoginValid = this.userManager.validateLogin(username, password);

        if (isLoginValid) {
          response = ServerCommands.loginCommand("Login erfolgreich!", true);
        } else {
          response = ServerCommands
                  .loginCommand("Login fehlgeschlagen: Username oder Passwort inkorrekt", false);
        }
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

  public JSONObject chat(JSONObject request, User user) {
    JSONObject response = new JSONObject();

    if (request.containsKey("message") && user != null) {
      String message = (String) request.get("message");

      response = ServerCommands.chatCommand(user.getUsername(), message);
    }
    return response;
  }

  public JSONObject whisper(JSONObject request, User user) {
    JSONObject response = new JSONObject();

    if (request.containsKey("message") && request.containsKey("to") && user != null) {
      String message = (String) request.get("message");

      response = ServerCommands.whisperCommand(user.getUsername(), message);
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
  public User getUser(String username) {
    return this.userManager.getUser(UserIdentifier.USERNAME, username);
  }

}