package socket;

import data.user.User;
import database.userdata.DBUserDataSource;
import events.EventReason;
import events.app.chat.ChatMessageEvent;
import events.app.chat.WhisperChatEvent;
import events.app.profil.ChangeProfilDataEvent;
import events.start.login.LoginEvent;
import events.start.registration.RegistrationEvent;
import java.util.List;
import lobby.Lobby;
import requests.profil.ChangeCredentialRequest;
import requests.chat.ChatRequest;
import requests.lobby.CreateRequest;
import requests.start.login.LoginRequest;
import requests.start.registration.RegisterRequest;
import requests.chat.WhisperRequest;
import user.UserIdentifier;

public class ClientAPI {

  private DBUserDataSource dbUserDataSource;

  public ClientAPI() {
    this.dbUserDataSource = new DBUserDataSource();
  }

  //Nur zum Testen. Verwendung von mock in JUnit
  public ClientAPI(DBUserDataSource dbUserDataSource) {
    this.dbUserDataSource = dbUserDataSource;
  }

  /**
   * Wenn Client eine Login-Anfrage sendet, werden die Logindaten
   * via validateLogin überprüft. Wenn die Daten korrekt sind, wird
   * eine Erfolgsnachricht an den Client gesendet.
   * Wenn Logindaten inkorrekt sind, wird eine Fehlermeldung an den
   * Client gesendet.
   *
   * @param request LoginRequest, das User-Daten für Login enthält
   * @param loggedUsers Liste eingeloggter User
   * @return loginEvent, welches den genauen Status der Verarbeitung der Anfrage enthält
   */
  public LoginEvent login(LoginRequest request, List<String> loggedUsers) {
    LoginEvent event = new LoginEvent();
    String username = request.getUsername();
    String password = request.getPassword();
    if (username != null && password != null) {
      if (loggedUsers.contains(username)) {
        event.setReason(EventReason.ALREADY_LOGGED_IN);
        event.setSuccess(false);
      } else {
        boolean isLoginValid = this.dbUserDataSource.validateLogin(username, password);
        if (isLoginValid) {
          event.setReason(EventReason.LOGIN_SUCCESSFUL);
        } else {
          event.setReason(EventReason.NAME_OR_PASSWORD_WRONG);
        }
        event.setSuccess(isLoginValid);
      }
    } else {
      event.setReason(EventReason.INVALID_REQUEST);
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
   * @param request RegisterRequest, das User-Daten für Registrierung enthält
   * @return registerEvent, das entweder Erfolg oder Misserfolg als Nachricht enthält
   */
  public RegistrationEvent register(RegisterRequest request) {
    RegistrationEvent event = new RegistrationEvent();
    String username = request.getUsername();
    String password = request.getPassword();
    String email = request.getEmail();
    if (username != null && password != null && email != null) {
      boolean createUser = this.dbUserDataSource.createUser(username, password, email);
      if (createUser) {
        event.setReason(EventReason.REGISTRATION_SUCCESSFUL);
      } else {
        event.setReason(EventReason.REGISTRATION_FAILED_USER_OR_EMAIL_EXISTS);
      }
      event.setSuccess(createUser);
    } else {
      event.setReason(EventReason.INVALID_REQUEST);
      event.setSuccess(false);
    }
    return event;
  }

  private boolean changeCredential(User user, UserIdentifier ui, String newCred) {
    return dbUserDataSource.changeUser(user, ui, newCred);
  }

  public ChangeProfilDataEvent changeCredential(ChangeCredentialRequest request, User user) {
    ChangeProfilDataEvent event = new ChangeProfilDataEvent();
    String newCred = request.getCredential();
    Integer type = request.getCrednr();
    String what = "";
    boolean changeCredential = false;
    if (newCred != null && type == 1 || type == 2) {
      if (type == 1) {
        what = UserIdentifier.EMAIL.toString();
        changeCredential = changeCredential(user, UserIdentifier.EMAIL, newCred);
      }
      if (type == 2) {
        what = UserIdentifier.PASSWORD.toString();
        changeCredential = changeCredential(user, UserIdentifier.PASSWORD, newCred);
      }
      event.setSuccess(changeCredential);
      event.setMsg(what + " wurde " + (changeCredential ? "erfolgreich" : "nicht") + " geändert");
    } else {
      event.setMsg("Fehler aufgetreten");
      event.setSuccess(false);
    }
    return event;
  }

  public ChatMessageEvent chat(ChatRequest request, User user) {
    ChatMessageEvent event = new ChatMessageEvent();
    if (request.getMsg() != null && user != null) {
      event.setMsg(request.getMsg());
      event.setUser(user.getUsername());
      event.setLobbyId(request.getLobbyId());
    }
    return event;
  }

  public WhisperChatEvent whisper(WhisperRequest request, User user) {
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
   * @param request CreateRequest, enthält alle Daten der zu erstellenden Lobby
   * @param user User, der die Lobby erstellen möchte
   * @return Lobby die gerade erstellt wurde und gibt diese an den Clientlistener Thread
   */
  public Lobby createLobby(CreateRequest request, User user, Server server) {
    String name = request.getName();
    int size = request.getSize();
    String password = request.getPassword();
    if (password == null) {
      password = "";
    }
    return new Lobby(size, user, name, password, server);
  }
}