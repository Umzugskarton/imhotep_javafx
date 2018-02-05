package connection;

import com.google.common.eventbus.EventBus;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import ui.dialog.serversetting.ShowServerSettingsEvent;

public class ConnectionInput {

  private Socket socket;
  private ObjectInputStream objectInputStream;

  private ConnectionInputThread inputThread;

  private final EventBus eventBus;

  public ConnectionInput(Socket socket, EventBus eventBus) {
    this.socket = socket;
    this.eventBus = eventBus;
    init();
  }

  private void init() {
    try {
      this.objectInputStream = new ObjectInputStream(socket.getInputStream());
      this.inputThread = new ConnectionInputThread(this.objectInputStream, this.eventBus);
      this.inputThread.start();
    } catch (IOException e) {
      this.eventBus.post(new ShowServerSettingsEvent());
    }
  }
}
