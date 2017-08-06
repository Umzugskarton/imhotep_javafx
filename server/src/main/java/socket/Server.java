package socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import json.ServerCommands;
import lobby.Lobby;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

  public JSONObject addLobby(Lobby lobby){
    log.info("Eine neue Lobby wurde erstellt");
    this.openLobby.add(lobby);

    JSONObject j = ServerCommands.createCommand("Lobby Erfolgreich erstellt!", true, openLobby.size()-1);
    return j;
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

  public void sendToAll(JSONObject json) {
    for (ClientListener clientListener : connectedClients) {
      clientListener.send(json);
    }
  }

  public boolean sendTo(JSONObject json, String to) {
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

  public void sendToLoggedIn(JSONObject json) {
    for (ClientListener clientListener : connectedClients) {
      if (clientListener.isLoggedIn()) {
        clientListener.send(json);
      }
    }
  }

  public Lobby getLobbybyID(int id){
    return this.openLobby.get(id);
  }
  public JSONObject getLobbies(){
    JSONArray lobbies = new JSONArray();
    for (Lobby lobby: openLobby) {
      JSONObject tempLobby= new JSONObject();
      tempLobby.put("name", lobby.getName());
      tempLobby.put("lobbyid", openLobby.indexOf(lobby));
      tempLobby.put("size", lobby.getSize());
      tempLobby.put("usercount", lobby.getUserCount());
      tempLobby.put("haspw", lobby.hasPW());
      lobbies.add(tempLobby);
    }
    return ServerCommands.lobbylistCommand(lobbies);
  }


  public JSONObject getLoggedUsers() {
    JSONArray users = new JSONArray();
    for (ClientListener client : connectedClients) {
      if (client.isLoggedIn()) {
        users.add(client.getUser().getUsername());
      }
    }
    return ServerCommands.userlistCommand(users);
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
