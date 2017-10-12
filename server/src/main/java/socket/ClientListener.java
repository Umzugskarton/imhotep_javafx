package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import socket.commands.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import socket.commands.CommandFactory;
import user.User;

public class ClientListener implements Runnable {

  private final Logger log = LoggerFactory.getLogger(getClass().getName());

  private Server server = null;
  private Socket clientSocket = null;
  private ClientAPI clientAPI = null;
  private PrintWriter out = null;
  private BufferedReader in = null;
  private User user = null;

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
        log.info("Nachricht erhalten: " + receivedMsg);

        JSONParser parser = new JSONParser();
        try {
          Object obj = parser.parse(receivedMsg);
          JSONObject request = (JSONObject) obj;

          if (request.containsKey("command")) {
            String command = (String) request.get("command");
            CommandFactory commandFactory = new CommandFactory(this, request);
            Command c = commandFactory.getCommand(command);
            Invoker invoker = new Invoker(c);
            invoker.call();
          }
        } catch (ParseException pe) {
          log.error("Ungültige Nachricht erhalten " + receivedMsg, pe);
        }
      }
    } catch (IOException ex) {
      log.error("Ein Fehler ist aufgetreten", ex);
    } finally {
      if (this.isLoggedIn()) {
        this.user = null;
        //this.server.sendToAll(server.getLoggedUsers());
        userlistCommand c = new userlistCommand(this );
      }

      this.server.removeClient(this);
    }
  }

  public void send(String json) {
    if (this.out != null) {

      log.info(
          "Nachricht gesendet: " + json);
      this.out.println(json);
      this.out.flush();
    }
  }

  public boolean isLoggedIn() {
    return this.user != null;
  }

  public User getUser() {
    return this.user;
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