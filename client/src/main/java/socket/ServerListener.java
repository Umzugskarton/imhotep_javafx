package socket;

import com.google.common.eventbus.EventBus;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import main.SceneController;
import main.events.EventFactory;
import main.events.voidEvent;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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

      BufferedReader in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));

      String receivedMsg;
      while ((receivedMsg = in.readLine()) != null) {
        log.info("Nachricht erhalten: " + receivedMsg);

        JSONParser parser = new JSONParser();
        try {
          Object obj = parser.parse(receivedMsg);
          JSONObject request = (JSONObject) obj;
            EventFactory eventFactory = new EventFactory();
            voidEvent event = eventFactory.getEvent(request);
            this.eventBus.post(event);

        } catch (ParseException pe) {
          log.error("Ungültige Nachricht erhalten " + receivedMsg, pe);
        }
      }
      log.info("Serverthread " + Thread.currentThread().getId() + " beendet!");
    } catch (IOException ex) {
      log.error("Ein Fehler ist aufgetreten", ex);
    }
  }
}
