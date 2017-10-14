package socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import SRVevents.createEvent;
import SRVevents.lobbylistEvent;
import SRVevents.userListEvent;
import SRVevents.voidEvent;
import commonLobby.CLTLobby;
import commonLobby.LobbyUser;
import lobby.Lobby;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import user.User;

public class Server {

  private final Logger log = LoggerFactory.getLogger(getClass().getName());

  private int port;
  private ServerSocket serverSocket = null;
  private ClientAPI clientAPI = null;
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

  public void addClient(Socket clientSocket) {
    log.info("Ein neuer Client hat sich verbunden");

    ClientListener clientListener = new ClientListener(this, clientSocket, this.clientAPI);
    Thread thread = new Thread(clientListener);
    thread.start();

    log.info("Thread " + thread.getId() + " gestartet");

    this.connectedClients.add(clientListener);

    log.info(
        "Thread " + thread.getId() + " zur Liste der verbundenen Clients hinzugefügt");
  }

  public void removeClient(ClientListener clientListener) {
    log.info("Thread " + clientListener.getThread().getId()
        + ": Client hat die Verbindung beendet");

    if (this.connectedClients.contains(clientListener)) {
      this.connectedClients.remove(clientListener);

      log.info("Thread " + clientListener.getThread().getId()
          + " von der Liste der verbundenen Clients entfernt");
    }
  }

  public void sendToAll(voidEvent json) {
    for (ClientListener clientListener : connectedClients) {
      clientListener.send(json);
    }
  }

  public boolean sendTo(voidEvent json, String to) {
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

  public createEvent addLobby(Lobby lobby){
    log.info("Eine neue Lobby wurde erstellt");
    this.openLobby.add(lobby);
    lobby.setLobbyID(openLobby.size()-1);

    createEvent j = new createEvent( true, openLobby.size()-1,"Lobby Erfolgreich erstellt!");
    return j;
  }

  public Lobby getLobbybyID(int id){
    for(Lobby lobby : openLobby) {
      if(lobby.getLobbyID() == id) {
        return lobby;
      }
    }
    return null;
  }

  public lobbylistEvent getLobbies(){
    lobbylistEvent lobbies = new lobbylistEvent();
    ArrayList<CLTLobby> CLTLobbies = new ArrayList<>();
    for (Lobby lobby: openLobby) {
      ArrayList<LobbyUser> users = new ArrayList<>();
      int i=0;
      for(User user : lobby.getUsers()){
          users.add(new LobbyUser(user.getUsername(),lobby.getColors().get(i), lobby.getReady()[i]));
          i++;
      }
      CLTLobby tempLobby= new CLTLobby(lobby.getLobbyID(),
              lobby.getSize(),
              lobby.getName(),
              lobby.hasPW(),
              lobby.getUserCount(),
              users
              );
      CLTLobbies.add(tempLobby);
    }
    return lobbies;
  }



  public void sendToLoggedIn(voidEvent event) {
    for (ClientListener clientListener : connectedClients) {
      if (clientListener.isLoggedIn()) {
        clientListener.send(event);
      }
    }
  }

  public userListEvent getLoggedUsers() {
    userListEvent  event = new userListEvent();
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
