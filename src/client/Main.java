package client;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application{

    private Stage stage;

    public static void main(String[] args){
        launch();       //Startet Java-FX-Thread und ruft die "start"-Methode auf
    }

    public void start(Stage stage){

        GridPane grid = new GridPane();
        Scene login = new Scene(grid);
        stage.show();
        stage.setScene(login);
        stage.setTitle("Imhotep");
        stage.setHeight(250);
        stage.setWidth(350);

        Label labelUser = new Label("Benutzername: ");
        TextField userName = new TextField();
        userName.setPromptText("Benutzernamen eingeben");

        Label labelPassword = new Label("Passwort: ");
        PasswordField passwordUser = new PasswordField();
        passwordUser.setPromptText("Passwort eingeben");

        Button loginNow = new Button("Login");
        Button registerNow = new Button("Register");

        GridPane.setConstraints(labelUser,0,0);
        GridPane.setConstraints(userName,1,0);
        GridPane.setConstraints(labelPassword,0,1);
        GridPane.setConstraints(passwordUser,1,1);
        GridPane.setConstraints(loginNow,0,2);
        GridPane.setConstraints(registerNow,1,2);

        grid.getChildren().addAll(labelUser, userName, labelPassword, passwordUser, loginNow, registerNow);
    }
}
