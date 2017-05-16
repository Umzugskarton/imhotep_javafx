import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
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

        JSONObject obj = new JSONObject();

        obj.put("command", "register");
        obj.put("username", "testjson2");
        obj.put("password", "testjson");
        obj.put("email", "testemail2@json.de");

        CSocket.send(obj);
        launch(args);
        CSocket.close();
    }
}
