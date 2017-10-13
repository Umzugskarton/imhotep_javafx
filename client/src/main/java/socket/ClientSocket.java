package socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import CLTrequests.Request;
import com.google.common.eventbus.EventBus;
import com.google.gson.Gson;
import main.SceneController;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientSocket {

  private final Logger log = LoggerFactory.getLogger(getClass().getName());

  // SceneController
  private SceneController sceneController = null;
  // Server
  private ServerListener serverListener = null;
  private Socket serverSocket = null;

  // Socketdaten
  private String host = null;
  private int port;

  // Writer
  private PrintWriter out = null;

  // Eventbus
  private EventBus eventBus;

  // Gson caster
  private Gson gson = new Gson();

  public ClientSocket(SceneController sceneController , EventBus eventBus) {
    this.sceneController = sceneController;
    this.eventBus = eventBus;
    this.host = "localhost";
    this.port = 47096;
    this.init();
  }

  private void init() {
    try {
      this.serverSocket = new Socket(this.host, this.port);
      this.out = new PrintWriter(this.serverSocket.getOutputStream(), true);
      this.serverListener = new ServerListener(serverSocket, sceneController, eventBus);
      Thread serverThread = new Thread(this.serverListener);
      serverThread.start();
    } catch (UnknownHostException ex) {
      log.error(
          "UnknownHostException bei Verbindung zu Host bei Host: " + this.host + " und Port: "
              + this.port, ex);
      System.exit(-1);
    } catch (IOException ex) {
      log.error("IOException bei Verbindung zu Host bei Host: " + this.host
          + " und Port: " + this.port, ex);
      System.exit(-1);
    }
  }

  public void send(Request json) {
    String jsonString = gson.toJson(json);

    this.out.println(jsonString);
    this.out.flush();

    log.info("Nachricht gesendet: " + jsonString);
  }

  public void close() {
    try {
      this.serverSocket.close();
    } catch (IOException e) {
      log.error("Konnte Verbindung nicht schließen", e);
    }
  }
}
