package socket;

import CLTrequests.*;
import SRVevents.*;
import lobby.Lobby;
import user.User;
import user.UserIdentifier;
import database.userdata.DBUserDataSource;

import java.util.ArrayList;

public class ClientAPI {

  private DBUserDataSource dbUserDataSource;

  public ClientAPI() {
    this.dbUserDataSource = new DBUserDataSource();
  }

  /**
   * Wenn Client eine Login-Anfrage sendet, werden die Logindaten
   * via validateLogin überprüft. Wenn die Daten korrekt sind, wird
   * eine Erfolgsnachricht an den Client gesendet.
   * Wenn Logindaten inkorrekt sind, wird eine Fehlermeldung an den
   * Client gesendet.
   *
   * @param request     JSON-Objekt, das User-Daten für Login enthält;
   * @param loggedUsers JSON-Objekt, Liste eingeloggter User
   */
  public loginEvent login(loginRequest request, ArrayList<String> loggedUsers) {
    loginEvent event = new loginEvent();
    String username = request.getUsername();
    String password = request.getPassword();
    if (username != null && password != null) {
      if (loggedUsers.contains(username)) {
        event.setMsg("Login fehlgeschlagen: Bereits eingeloggt!");
        event.setSuccess(false);
      } else {
        boolean isLoginValid = this.dbUserDataSource.validateLogin(username, password);

        if (isLoginValid) {
          event.setMsg("Login erfolgreich!");
          event.setSuccess(true);
        } else {
          event.setMsg("Login fehlgeschlagen: Username oder Passwort inkorrekt");
          event.setSuccess(false);
        }
      }
    } else {
      event.setMsg("Login fehlgeschlagen: Ungültige Anfrage");
      event.setSuccess(false);
    }
    return event;
  }

  /**
   * Wenn Client eine Registrierungs-Anfrage sendet, versucht der dbUserDataSource
   * einen neuen Benutzer anzulegen. Wenn die Erstellung erfolgreich war, wird
   * eine Erfolgsnachricht an den Client gesendet.
   * Wenn die Erstellung nicht erfolgreich war, wird eine Fehlermeldung an den
   * Client gesendet.
   *
   * @param request JSON-Objekt, das User-Daten für Registrierung enthält
   * @return JSON-Objekt, das entweder Erfolg oder Misserfolg als Nachricht enthält
   */
  public registerEvent register(registerRequest request) {
    registerEvent event = new registerEvent();
    String username = request.getUsername();
    String password = request.getPassword();
    String email = request.getEmail();
    if (username != null && password != null && email != null) {

      boolean createUser = this.dbUserDataSource.createUser(username, password, email);

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

  public chatEvent chat(chatRequest request, User user) {
    chatEvent event = new chatEvent();
    if (request.getMsg() != null && user != null) {
      event.setMsg(request.getMsg());
      event.setUser(user.getUsername());

    }
    return event;
  }

  public whisperEvent whisper(whisperRequest request, User user) {
    whisperEvent event = new whisperEvent();
    if (request.getMsg() != null && request.getTo() != null && user != null) {
      event.setMsg(request.getMsg());
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
    return this.dbUserDataSource.getUser(UserIdentifier.USERNAME, username);
  }


  public Lobby createLobby(createRequest request, User user) {
    String name = request.getName();
    int size = request.getSize();
    Lobby lobby = new Lobby(size, user, name);

    if (request.getPassword() != null) {
      lobby.setPassword(request.getPassword());
    }

    return lobby;
  }
}