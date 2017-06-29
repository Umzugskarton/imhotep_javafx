package socket;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

import org.json.simple.JSONObject;
import main.SceneController;
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

  public ClientSocket(SceneController sceneController) {
    this.sceneController = sceneController;
    this.host = "localhost";
    this.port = 47096;
    this.init();
  }

  private void init() {
    try {
      this.serverSocket = new Socket(this.host, this.port);
      this.out = new PrintWriter(this.serverSocket.getOutputStream(), true);
      this.serverListener = new ServerListener(serverSocket, sceneController);
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

  public void send(JSONObject json) {
    String jsonString = json.toString();

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
