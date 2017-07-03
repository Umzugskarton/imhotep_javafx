package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import json.ServerCommands;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
            JSONObject response = null;
            if (!(command.equals("register") || command.equals("login")) && this.user==null) {
              return;
            }
            switch (command) {
              case "register":
                response = this.clientAPI.register(request);
                break;
              case "login":
                response = this.clientAPI.login(request, this.server.getLoggedUsers());
                if ((boolean) response.get("success")) {
                  this.user = this.clientAPI.getUser((String) request.get("username"));
                  this.server.sendToLoggedIn(this.server.getLoggedUsers());
                }

                break;
              case "userlist":
                response = this.server.getLoggedUsers();
                break;
              case "chat":
                JSONObject chatMessage = this.clientAPI.chat(request, this.user);
                this.server.sendToLoggedIn(chatMessage);
                break;
              case "whisper":
                String receiverUsername = this.server
                    .getLoggedInUsername((String) request.get("to"));
                if (receiverUsername != null) {
                  chatMessage = this.clientAPI.whisper(request, this.user);
                  this.server.sendTo(chatMessage, receiverUsername);
                } else {
                  response = ServerCommands.userNotFoundError((String) request.get("to"));
                }
                break;
              case "logout":
                this.user = null;
                break;
            }
            if (response != null) {
              this.send(response);
            }
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
        this.server.sendToAll(server.getLoggedUsers());
      }

      this.server.removeClient(this);
    }
  }

  public void send(JSONObject json) {
    if (this.out != null) {
      String jsonString = json.toString();

      log.info(
          "Nachricht gesendet: " + jsonString);
      this.out.println(jsonString);
      this.out.flush();
    }
  }

  public boolean isLoggedIn() {
    return this.user != null;
  }

  public User getUser() {
    return this.user;
  }

  public Thread getThread() {
    return Thread.currentThread();
  }
}