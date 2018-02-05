package connection;

import com.google.common.eventbus.EventBus;
import requests.IRequest;

public class ConnectionDebug extends Connection {

  private final EventBus eventBus;

  public ConnectionDebug(EventBus eventBus) {
    super();
    this.eventBus = eventBus;
  }

  @Override
  public void send(IRequest request) {
    //EventBus für Debug-Zwecke!
    this.eventBus.post(request);
        /*

        try {
            this.output.send(request);
        }catch (ConnectionErrorExeption e){
            logger.error("Verbindung unterbrochen");
            closeConnection();
        }
        */
  }
}
