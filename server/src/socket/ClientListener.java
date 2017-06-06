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
import user.User;

public class ClientListener implements Runnable {

  private Server server = null;
  private Socket clientSocket = null;
  private ClientAPI clientAPI = null;
  private PrintWriter out = null;
  private BufferedReader in = null;
  private User user =null;

  public ClientListener(Server server, Socket clientSocket, ClientAPI clientAPI) {
    this.server = server;
    this.clientSocket = clientSocket;
    this.clientAPI = clientAPI;

    try {
      this.out = new PrintWriter(this.clientSocket.getOutputStream(), true);
      this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    } catch (IOException ex) {
      System.out.println("[SERVER] Fehler: " + ex.getMessage());
    }
  }

  @Override
  public void run() {
    try {
      String receivedMsg = null;
      while ((receivedMsg = in.readLine()) != null) {
        System.out.println(
            "[SERVER] Thread " + Thread.currentThread().getId() + ": Nachricht erhalten: "
                + receivedMsg);

        JSONParser parser = new JSONParser();
        try {
          Object obj = parser.parse(receivedMsg);
          JSONObject request = (JSONObject) obj;

          if (request.containsKey("command")) {
            String command = (String) request.get("command");
            JSONObject response = null;
            switch(command){
              case "register":
                response = this.clientAPI.register(request);
                break;
              case "login":
                response = this.clientAPI.login(request);
                if ((boolean)response.get("success")){
                  this.user = this.clientAPI.getUser((String) request.get("username"));
                  this.server.sendToAll(this.server.getLoggedUsers());
                }
                break;
              case "userlist":
                response = this.server.getLoggedUsers();
                break;
                case "logout":
                  this.user=null;
            }
            this.send(response);
          }
        } catch (ParseException pe) {
          System.out.println("[SERVER] Thread " + Thread.currentThread().getId()
              + ": Ungültige Nachricht erhalten " + receivedMsg + ": " + pe);
        }
      }
    } catch (IOException ex) {
      System.out
          .println("[SERVER] Thread " + Thread.currentThread().getId() + ": " + ex.getMessage());
    } finally {
    if (this.isLoggedIn()){
      this.user= null;
      this.server.sendToAll(server.getLoggedUsers());
    }
      this.server.removeClient(this);
    }
  }

  public void send(JSONObject json) {
    if (this.out != null) {
      String jsonString = json.toString();

      System.out.println(
          "[SERVER] Thread " + Thread.currentThread().getId() + ": Nachricht gesendet: "
              + jsonString);
      this.out.println(jsonString);
      this.out.flush();
    }
  }

  public boolean isLoggedIn(){
      return this.user != null;
  }

  public User getUser(){return this.user;}

  public Thread getThread() {
    return Thread.currentThread();
  }
}