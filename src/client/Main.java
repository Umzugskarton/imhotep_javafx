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
        stage.show(); //stage wird angezeigt
        stage.setScene(login); //hier wird die szene übergeben
        stage.setTitle("Imhotep"); //title des fensters
        stage.setHeight(250); //maße des fensters
        stage.setWidth(350);

        Label labelUser = new Label("Benutzername: "); //Label und Textfelder für den Benutzer
        TextField userName = new TextField();
        userName.setPromptText("Benutzernamen eingeben");

        Label labelPassword = new Label("Passwort: "); //Label und Textfelder für das Passwort
        PasswordField passwordUser = new PasswordField();
        passwordUser.setPromptText("Passwort eingeben");

        Button loginNow = new Button("Login"); //Buttons werden angelegt
        Button registerNow = new Button("Register");

        GridPane.setConstraints(labelUser,0,0); //Hier werden die Positionen "angelegt"
        GridPane.setConstraints(userName,1,0);
        GridPane.setConstraints(labelPassword,0,1);
        GridPane.setConstraints(passwordUser,1,1);
        GridPane.setConstraints(loginNow,0,2);
        GridPane.setConstraints(registerNow,1,2);

        grid.getChildren().addAll(labelUser, userName, labelPassword, passwordUser, loginNow, registerNow); //hier werden dem grid die buttons, textfelder und labels übergeben
    }
}
