package socket;

import events.Event;
import com.google.common.eventbus.EventBus;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import main.SceneController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerListener implements Runnable {

  private final Logger log = LoggerFactory.getLogger(getClass().getName());

  private Socket serverSocket;
  private SceneController sceneController;
  private EventBus eventBus;

  public ServerListener(Socket serverSocket, SceneController sceneController, EventBus eventBus) {
    this.serverSocket = serverSocket;
    this.sceneController = sceneController;
    this.eventBus = eventBus;
  }

  @Override
  public void run() {
    try {
      log.info("Serverthread gestartet!");

      ObjectInputStream in = new ObjectInputStream(serverSocket.getInputStream());
      Object event;

      try {
        while ((event = in.readObject()) != null){
          log.debug("Nachricht erhalten: " + event);

          if(event instanceof Event){
            this.eventBus.post(event);
          }
          else {
            log.error("Nachricht konnte nicht gelesen werden");
          }
        }
        log.info("Serverthread " + Thread.currentThread().getId() + " beendet!");
      } catch (ClassNotFoundException e){
        log.error("Klasse wurde nicht gefunden!",e);
      } catch (IOException e) {
        log.error("Objekt konnte nicht gelesen werden!" , e);
      }
    } catch (IOException ex) {
      log.error("ObjektInputStream konnte nicht geöffnet werden!", ex);
    }
  }
}