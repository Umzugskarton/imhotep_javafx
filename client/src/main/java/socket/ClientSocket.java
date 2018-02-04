package socket;

import com.google.common.eventbus.EventBus;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import main.SceneController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import requests.IRequest;

public class ClientSocket {

  private final Logger log = LoggerFactory.getLogger(getClass().getName());

  // SceneController
  private SceneController sceneController;
  // Server
  private ServerListener serverListener = null;
  private Socket serverSocket = null;

  // Socketdaten
  private String host;
  private int port;

  // Writer
  private ObjectOutputStream out = null;

  // Eventbus
  private EventBus eventBus;

  public ClientSocket(SceneController sceneController, EventBus eventBus) {
    this.sceneController = sceneController;
    this.eventBus = eventBus;
    this.host = "localhost";
    this.port = 47096;
    this.init();
  }

  private void init() {
    try {
      this.serverSocket = new Socket(this.host, this.port);
      this.out = new ObjectOutputStream(this.serverSocket.getOutputStream());
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

  public void send(IRequest request) {
    try {
      this.out.writeObject(request);
      this.out.flush();
    } catch (IOException e) {
      log.error("IO-Fehler beim senden vom Request", e);
    }
    log.debug("Nachricht gesendet: " + request.getType());
  }

  public void close() {
    try {
      this.serverSocket.close();
    } catch (IOException e) {
      log.error("Konnte Verbindung nicht schließen", e);
    }
  }
}
