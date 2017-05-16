package main;

import javafx.application.Application;
import javafx.stage.Stage;
import socket.ClientSocket;

public class Main extends Application{

    private Stage stage;

    public static void main(String[] args){

        launch();       //Startet Java-FX-Thread und ruft die "start"-Methode auf
    }

    public void start(Stage stage) {
        ClientSocket clientSocket = new ClientSocket();
        SceneController sc = new SceneController(stage, clientSocket);
    }
}
