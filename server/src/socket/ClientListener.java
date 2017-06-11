package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientListener implements Runnable {

  private final Logger log = LoggerFactory.getLogger(getClass().getName());

  private Server server = null;
  private Socket clientSocket = null;
  private ClientAPI clientAPI = null;
  private PrintWriter out = null;
  private BufferedReader in = null;

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
      String receivedMsg = null;
      while ((receivedMsg = in.readLine()) != null) {
        log.info("Nachricht erhalten: "+ receivedMsg);

        JSONParser parser = new JSONParser();
        try {
          Object obj = parser.parse(receivedMsg);
          JSONObject request = (JSONObject) obj;

          if (request.containsKey("command")) {
            String command = (String) request.get("command");
            JSONObject response = null;

            if (command.equals("register")) {
              response = this.clientAPI.register(request);
            } else if (command.equals("login")) {
              response = this.clientAPI.login(request);
            }

            this.send(response);
          }
        } catch (ParseException pe) {
          log.error("Ungültige Nachricht erhalten " + receivedMsg, pe);
        }
      }
    } catch (IOException ex) {
      log.error("Ein Fehler ist aufgetreten", ex);
    } finally {
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

  public Thread getThread() {
    return Thread.currentThread();
  }
}