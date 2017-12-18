package socket;

import com.google.gson.Gson;
import CLTrequests.Request;
import CLTrequests.RequestFactory;
import socket.commands.Command;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

import GameMoves.MoveFactory;
import SRVevents.Event;
import lobby.Lobby;
import socket.commands.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import socket.commands.Invoker;
import socket.commands.CommandFactory;
import user.User;

/**
 * Enthält alle wichtigen Objekte zur und über die Kommunikation mit dem dazugehörigen Client.
 *  Wartet auf ankommende Nachrichten, und verarbeitet diese dann, mithilfe von Gson
 *  und einer Factory, die Nachrichten werden zu Requests oder Moves zurückgeschlüsselt.
 *  Dafür wird dann bei einer Request der zugehörige Command auch mit einer Factory initiert
 *  und durch das Invoker Objekt (Command Pattern) ausgeführt.
 *  ist die eingehende Nachricht ein Move, wird dieser an das GameObjekt in der
 *  zugehörigen Lobby übergeben.
 */

public class ClientListener implements Runnable {

  private final Logger log = LoggerFactory.getLogger(getClass().getName());

  private Server server = null;
  private Socket clientSocket = null;
  private ClientAPI clientAPI = null;
  private PrintWriter out = null;
  private BufferedReader in = null;
  private User user = null;
  private Lobby lobby = null;
  private Gson gson= new Gson();

  public ClientListener(Server server, Socket clientSocket, ClientAPI clientAPI) {
    this.server = server;
    this.clientSocket = clientSocket;
    this.clientAPI = clientAPI;

    try {
      this.out = new PrintWriter(this.clientSocket.getOutputStream(), true);
      this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    } catch (IOException ex) {
      log.error("Ein Fehler ist aufgetreten", ex);
    }
  }

  @Override
  public void run() {
    try {
      String receivedMsg;
      while ((receivedMsg = in.readLine()) != null) {
        log.debug("Nachricht erhalten: " + receivedMsg);

        JSONParser parser = new JSONParser();
        try {
          Object obj = parser.parse(receivedMsg);
          JSONObject request = (JSONObject) obj;
          if (request.containsKey("request")) {
            RequestFactory ev = new RequestFactory();
            String command = (String) request.get("request");
            Request re = ev.getRequest(command);
            CommandFactory commandFactory = new CommandFactory(this);
            Command c = commandFactory.getCommand(gson.fromJson(request.toJSONString(), re.getClass()));
            Invoker invoker = new Invoker(c);
            invoker.call();
          } else if (request.containsKey("move")) {
            MoveFactory mf = new MoveFactory();
            if (this.lobby != null && !this.lobby.isVisible()) {
              lobby.getExecutor().setMove(mf.getMove((String) request.get("move")));
            }
          }
        } catch (ParseException pe) {
          log.error("Ungültige Nachricht erhalten " + receivedMsg, pe);
        }
      }
    } catch (SocketException ex) {
      if(this.user != null) {
        log.error("User " + this.user.getUsername() + " hat die Verbindung unerwartet beendet");
      } else {
        log.error("Client hat die Verbindung unerwartet beendet", ex);
      }
    } catch (IOException ex) {
      log.error("Ein Fehler ist aufgetreten", ex);
    } finally {
      if (this.isLoggedIn()) {
        if (lobby != null) {
          lobby.leave(user);
        }
      }
        this.user = null;
        this.server.sendToAll(server.getLoggedUsers());
      }

      this.server.removeClient(this);
  }

  public void send(Event event) {
    if (this.out != null) {
      Gson gson = new Gson();
      String json = gson.toJson(event);
      log.debug(
          "Nachricht gesendet: " + json);
      this.out.println(json);
      this.out.flush();
    }
  }

  public void setLobby(Lobby lobby){
    this.lobby= lobby;
  }

  public boolean isLoggedIn() {
    return this.user != null;
  }

  public User getUser() {
    return this.user;
  }

  public Lobby getLobby() {
    return lobby;
  }

  public void setUser(User user){
    this.user=user;
  }

  public Thread getThread() {
    return Thread.currentThread();
  }

  public ClientAPI getClientAPI(){return this.clientAPI;}

  public Server getServer(){return this.server;}
}