package socket;

import CLTrequests.loginRequest;
import SRVevents.*;
import org.json.simple.JSONObject;
import user.User;
import user.UserIdentifier;
import user.UserManager;

import java.util.ArrayList;

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
   */
  public loginEvent login(loginRequest request, ArrayList<String> loggedUsers) {
    loginEvent event = new loginEvent();
    String username = request.getUsername();
    String password = request.getPassword();
    if (username != null  && password != null) {
      if (loggedUsers.contains(username)) {
        event.setMsg("Login fehlgeschlagen: Bereits eingeloggt!");
        event.setSuccess(false);
      } else {
        boolean isLoginValid = this.userManager.validateLogin(username, password);

        if (isLoginValid) {
          event.setMsg("Login erfolgreich!");
          event.setSuccess(true);
        } else {
          event.setMsg("Login fehlgeschlagen: Username oder Passwort inkorrekt");
          event.setSuccess(false);
        }
      }
    }
    else{
      event.setMsg("Login fehlgeschlagen: Ungültige Anfrage");
      event.setSuccess(false);
    }
    return event;
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
  public registerEvent register(JSONObject request) {
    registerEvent event = new registerEvent();

    if (request.containsKey("username") && request.containsKey("password") && request
        .containsKey("email")) {
      String username = (String) request.get("username");
      String password = (String) request.get("password");
      String email = (String) request.get("email");

      boolean createUser = this.userManager.createUser(username, password, email);

      if (createUser) {
        event.setMsg("Registrierung erfolgreich!");
        event.setSuccess(createUser);
      } else {
        event.setMsg("Registrierung fehlgeschlagen: Username oder E-Mail existiert bereits");
        event.setSuccess(createUser);
      }
    } else {
      event.setMsg("Registrierung fehlgeschlagen: Ungültige Anfrage");
      event.setSuccess(false);
    }
    return event;
  }

  public chatEvent chat(JSONObject request, User user) {
    chatEvent event = new chatEvent();

    if (request.containsKey("message") && user != null) {
      event.setMsg((String) request.get("message"));
      event.setUser(user.getUsername());

    }
    return event;
  }

  public whisperEvent whisper(JSONObject request, User user) {

    whisperEvent event = new whisperEvent();

    if (request.containsKey("message") && request.containsKey("to") && user != null) {
      event.setMsg((String) request.get("message"));
      event.setFrom(user.getUsername());
    }
    return event;
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