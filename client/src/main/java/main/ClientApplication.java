package main;

import javafx.application.Application;
import javafx.stage.Stage;
import socket.ClientSocket;
import sound.Soundtrack;


public class ClientApplication extends Application {

  private SceneController sceneController;

  // Socket, um bei Beenden des Clients Verbindung zu beenden
  private ClientSocket clientSocket;

  public static void main(String[] args) {
    launch();
  }

  @Override
  public void start(Stage stage) {
    this.sceneController = new SceneController(stage);
    this.clientSocket = sceneController.getClientSocket();
    Soundtrack.imhotepTheme.loop();
  }

  @Override
  public void stop() {
    this.clientSocket.close();
  }
}
