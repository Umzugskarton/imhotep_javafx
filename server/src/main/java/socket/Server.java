package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import SRVevents.*;
import commonLobby.CLTLobby;
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

  public Server() {
    this.port = 47096;
    this.clientAPI = new ClientAPI();
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

    if (this.connectedClients.contains(clientListener)) {
      this.connectedClients.remove(clientListener);
    }
  }

  public void sendToAll(Event json) {
    for (ClientListener clientListener : connectedClients) {
      clientListener.send(json);
    }
  }

  public boolean sendTo(Event json, String to) {
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
      toClient.send(json);
    }

    return found;
  }

  public createEvent addLobby(Lobby lobby) {
    log.info("Eine neue Lobby wurde erstellt");
    this.openLobby.add(lobby);
    lobby.setLobbyID(openLobby.size() - 1);

    createEvent j = new createEvent(true, openLobby.size() - 1, "Lobby Erfolgreich erstellt!");
    return j;
  }

  public Lobby getLobbybyID(int id) {
    for (Lobby lobby : openLobby) {
      if (lobby.getLobbyID() == id) {
        return lobby;
      }
    }
    return null;
  }

  public lobbylistEvent getLobbies(User user) {
    lobbylistEvent lobbies = new lobbylistEvent();
    ArrayList<CLTLobby> CLTLobbies = new ArrayList<>();
    for (Lobby lobby : openLobby) {
      if (lobby.isVisible()) {
        CLTLobby tempLobby = new CLTLobby(
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

  public userListEvent getLoggedUsers() {
    userListEvent event = new userListEvent();
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
      if (client.isLoggedIn()) {
        if (client.getUser().getUsername().toLowerCase().equals(username.toLowerCase())) {
          return client.getUser().getUsername();
        }
      }
    }

    return null;
  }
}
