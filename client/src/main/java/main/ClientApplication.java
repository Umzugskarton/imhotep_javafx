package main;

import javafx.application.Application;
import javafx.stage.Stage;
import socket.ClientSocket;

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
  }

  @Override
  public void stop() {
    this.clientSocket.close();
  }
}
