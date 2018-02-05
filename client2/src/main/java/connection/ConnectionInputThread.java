package connection;

import com.google.common.eventbus.EventBus;
import events.Event;
import java.io.IOException;
import java.io.ObjectInputStream;
import javafx.application.Platform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionInputThread extends Thread {

  private final Logger logger = LoggerFactory.getLogger(getClass().getName());

  private ObjectInputStream objectInputStream;
  private final EventBus eventBus;

  public ConnectionInputThread(ObjectInputStream ois, EventBus eventBus) {
    this.setDaemon(true);
    this.objectInputStream = ois;
    this.eventBus = eventBus;
  }

  @Override
  public void run() {
    try {
      Object o;
      while ((o = objectInputStream.readObject()) != null) {
        logger.info("Nachricht erhalten: " + o.getClass().getSimpleName());
        if (o instanceof Event) {
          Event event = (Event) o;
          Platform.runLater(
              () -> {
                this.eventBus.post(event);
              });
        } else {
          logger.error("Nachricht konnte nicht gelesen werden");
        }
      }
    } catch (IOException e) {
      logger.error("Ein Fehler ist aufgetreten", e);
      this.eventBus.post(new ConnectionErrorExeption());
    } catch (ClassNotFoundException e) {
      logger.error("Klasse wurde nicht gefunden", e);
    }
    logger.info("Serverthread " + Thread.currentThread().getId() + " beendet!");
  }
}
