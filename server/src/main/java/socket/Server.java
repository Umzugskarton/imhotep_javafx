package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import data.lobby.CommonLobby;
import events.Event;
import events.app.main.UserListEvent;
import events.app.lobby.CreateLobbyEvent;
import events.app.lobby.LobbyListEvent;
import lobby.Lobby;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import data.user.User;

public class Server {

  private final Logger log = LoggerFactory.getLogger(getClass().getName());

  private int port;
  private ServerSocket serverSocket = null;
  private ClientAPI clientAPI;
  private ArrayList<ClientListener> connectedClients = new ArrayList<>();
  private ArrayList<Lobby> openLobby = new ArrayList<>();

  public Server(ClientAPI clientApi, int port) {
    this.port = port;
    this.clientAPI = clientApi;
    this.init();
  }

  private void init() {
    try {
      this.serverSocket = new ServerSocket(this.port);
      log.info("Server auf Port " + this.port + " gestartet");
    } catch (IOException e) {
      log.error(
          "Server konnte auf Port " + this.port + " nicht gestartet werden", e);
      System.exit(-1);
    }
  }

  public void sendToLobby(Event e, Lobby lobby) {
    User[] users = lobby.getUsers();
    for (User tempUser : users) {
      if (tempUser != null) {
        sendTo(e, tempUser.getUsername());
      }
    }
  }

  public void run() {
    while (true) {
      try {
        Socket clientSocket = this.serverSocket.accept();
        this.addClient(clientSocket);
      } catch (IOException e) {
        log.error("Anfrage auf Port " + this.port + " konnte nicht verarbeitet werden", e);
        System.exit(-1);
      }
    }
  }

  private void addClient(Socket clientSocket) {
    ObjectOutputStream os;
    ObjectInputStream is;
    ClientListener clientListener = null;
    try {
      is = new ObjectInputStream(clientSocket.getInputStream());
      os = new ObjectOutputStream(clientSocket.getOutputStream());
      clientListener = new ClientListener(this, os, is, this.clientAPI);
    } catch (IOException e) {
      log.error("Fehler: ", e);
    }
    Thread thread = new Thread(clientListener);
    thread.start();

    log.info("[Thread " + thread.getId() + "] Ein neuer Client hat sich verbunden");

    this.connectedClients.add(clientListener);
  }

  public void removeClient(ClientListener clientListener) {
    log.info("[Thread " + clientListener.getThread().getId()
        + "] Client hat die Verbindung beendet");
    this.connectedClients.remove(clientListener);
  }

  public void sendToAll(Event event) {
    for (ClientListener clientListener : connectedClients) {
      clientListener.send(event);
    }
  }

  public boolean sendTo(Event event, String to) {
    boolean found = false;
    ClientListener toClient = null;
    for (ClientListener clientListener : connectedClients) {
      if (clientListener.getUser().getUsername().equals(to)) {
        toClient = clientListener;
        break;
      }
    }
    if (toClient != null) {
      found = true;
      toClient.send(event);
    }
    return found;
  }

  public CreateLobbyEvent addLobby(Lobby lobby) {
    log.info("Eine neue Lobby wurde erstellt");
    this.openLobby.add(lobby);
    lobby.setLobbyID(openLobby.size() - 1);
    return new CreateLobbyEvent(true, openLobby.size() - 1, "Lobby Erfolgreich erstellt!");
  }

  public Lobby getLobbybyID(int id) {
    for (Lobby lobby : openLobby) {
      if (lobby.getLobbyID() == id) {
        return lobby;
      }
    }
    return null;
  }

  public LobbyListEvent getLobbies(User user) {
    LobbyListEvent lobbies = new LobbyListEvent();
    ArrayList<CommonLobby> CLTLobbies = new ArrayList<>();
    for (Lobby lobby : openLobby) {
      if (lobby.isVisible()) {
        CommonLobby tempLobby = new CommonLobby(
            lobby.getLobbyID(),
            lobby.getName(),
            lobby.getLobbyUserArrayList(),
            lobby.hasPW(),
            lobby.getSize(),
            lobby.isHost(user),
            lobby.getHostName(),
            lobby.getReady(),
            lobby.getColors()
        );
        CLTLobbies.add(tempLobby);
      }
    }
    lobbies.setLobbies(CLTLobbies);
    return lobbies;
  }

  public void sendToLoggedIn(Event event) {
    for (ClientListener clientListener : connectedClients) {
      if (clientListener.isLoggedIn()) {
        clientListener.send(event);
      }
    }
  }

  public UserListEvent getLoggedUsers() {
    UserListEvent event = new UserListEvent();
    ArrayList<String> users = new ArrayList<>();
    for (ClientListener client : connectedClients) {
      if (client.isLoggedIn()) {
        users.add(client.getUser().getUsername());
      }
    }
    event.setUserList(users);
    return event;
  }

  public String getLoggedInUsername(String username) {
    for (ClientListener client : connectedClients) {
      if (client.isLoggedIn() && client.getUser().getUsername().equalsIgnoreCase(username)) {
        return client.getUser().getUsername();
      }
    }
    return null;
  }
}
