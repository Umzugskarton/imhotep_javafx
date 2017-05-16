import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import json.ClientCommands;
import socket.ClientSocket;
import org.json.simple.JSONObject;

public class ClientApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        ClientSocket CSocket = new ClientSocket();

        JSONObject registerCommand = ClientCommands
            .registerCommand("test", "test", "testemail@email.de");
        CSocket.send(registerCommand);

        JSONObject loginCommand = ClientCommands.loginCommand("test", "test");
        CSocket.send(loginCommand);

        launch(args);
        CSocket.close();
    }
}
