package socket;

import events.app.chat.WhisperChatEvent;
import events.app.profil.ChangeProfilDataEvent;
import events.app.chat.ChatMessageEvent;
import events.start.login.LoginEvent;
import events.start.registration.RegistrationEvent;
import requests.*;
import lobby.Lobby;
import data.user.User;
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
   * @param request loginRequest, das User-Daten für Login enthält
   * @param loggedUsers Liste eingeloggter User
   * @return loginEvent, welches den genauen Status der Verarbeitung der Anfrage enthält
   */
  public LoginEvent login(loginRequest request, ArrayList<String> loggedUsers) {
    LoginEvent event = new LoginEvent();
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
   * @param request registerRequest, das User-Daten für Registrierung enthält
   * @return registerEvent, das entweder Erfolg oder Misserfolg als Nachricht enthält
   */
  public RegistrationEvent register(registerRequest request) {
    RegistrationEvent event = new RegistrationEvent();
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
      }
      event.setSuccess(createUser);
    } else {
      event.setMsg("Registrierung fehlgeschlagen: Ungültige Anfrage");
      event.setSuccess(false);
    }
    return event;
  }


  public ChangeProfilDataEvent changeCredential(changeCredentialRequest request, User user) {
    ChangeProfilDataEvent event = new ChangeProfilDataEvent();
    String newCred = request.getCredential();
    Integer type = request.getCrednr();
    if (newCred != null) {
      if (type == 1) {
        boolean changeCredential = dbUserDataSource.changeUser(user, UserIdentifier.EMAIL, newCred);
        if (changeCredential) {
          event.setMsg("E-Mail wurde erfolgreich geändert");
          event.setSuccess(changeCredential);
        } else {
          event.setMsg("E-Mail wurde nicht geändert!");
          event.setSuccess(changeCredential);
        }
      }
      if (type == 2) {
        boolean changeCredential = dbUserDataSource
                .changeUser(user, UserIdentifier.PASSWORD, newCred);
        if (changeCredential) {
          event.setMsg("Passwort wurde erfolgreich geändert");
          event.setSuccess(changeCredential);
        } else {
          event.setMsg("Passwort wurde nicht geändert");
          event.setSuccess(changeCredential);
        }
      }
      if (type == 3) {
        boolean changeCredential = dbUserDataSource
                .changeUser(user, UserIdentifier.USERNAME, newCred);
        if (changeCredential) {
          event.setMsg("Username wurde erfolgreich geändert");
          event.setSuccess(changeCredential);
        } else {
          event.setMsg("Username wurde nicht geändert");
          event.setSuccess(changeCredential);
        }
      }
    } else {
      event.setMsg("Fehler aufgetreten");
      event.setSuccess(false);
    }
    return event;
  }



  public ChatMessageEvent chat(chatRequest request, User user) {
    ChatMessageEvent event = new ChatMessageEvent();
    if (request.getMsg() != null && user != null) {
      event.setMsg(request.getMsg());
      event.setUser(user.getUsername());
      if (request.getLobbyId() != null) {
        event.setLobbyId(request.getLobbyId());
      }
    }
    return event;
  }

  public WhisperChatEvent whisper(whisperRequest request, User user) {
    WhisperChatEvent event = new WhisperChatEvent();
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

  /**
   * Wenn ein Client eine Anfrage zur Erstellung einer Lobby schickt
   * wird hier das Lobby-Objekt erstellt und an den ClientListener zurückgegeben
   *
   * @param request createRequest, enthält alle Daten der zu erstellenden Lobby
   * @param user User, der die Lobby erstellen möchte
   * @return Lobby die gerade erstellt wurde und gibt diese an den Clientlistener Thread
   */
  public Lobby createLobby(createRequest request, User user) {
    String name = request.getName();
    int size = request.getSize();
    Lobby lobby = new Lobby(size, user, name);

    if (request.getPassword() != null && !request.getPassword().isEmpty()) {
      lobby.setPassword(request.getPassword());
    }

    return lobby;
  }
}