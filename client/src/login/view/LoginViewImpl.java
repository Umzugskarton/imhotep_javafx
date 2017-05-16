package login.view;

import static javafx.application.Application.launch;
import json.ClientCommands;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import login.presenter.LoginPresenter;
import main.SceneController;
import org.json.simple.JSONObject;


public class LoginViewImpl implements LoginView {
    public static void main(String[] args){
        launch(args);
    }

    private Scene loginScene;
    private LoginPresenter loginPresenter;
    private SceneController sc;
    private Label loginStatus;

    public LoginViewImpl(){
        buildLogin();
    }

    public void buildLogin() { //created by mircoskrzipczyk, annkristinklopp

        GridPane grid = new GridPane();
        loginScene = new Scene(grid);

        Label labelUser = new Label("Benutzername: "); //Label und Textfelder für den Benutzer
        TextField userName = new TextField();
        userName.setPromptText("Benutzernamen eingeben");

        Label labelPassword = new Label("Passwort: "); //Label und Textfelder für das Passwort
        PasswordField passwordUser = new PasswordField();
        passwordUser.setPromptText("Passwort eingeben");

        loginStatus = new Label();    //gibt an, ob Login erfolgreich war

        Button loginNow = new Button("Login"); //Buttons werden angelegt

        loginNow.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event){
                JSONObject loginCommand = new ClientCommands().loginCommand(userName.getText(), passwordUser.getText());
                loginPresenter.getSceneController().getClientSocket().send(loginCommand);
            }
        });

        Button registerNow = new Button("Register");

        registerNow.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event){
                loginPresenter.getSceneController().toRegistrationScene();
            }
        });


        GridPane.setConstraints(labelUser, 0, 0); //Hier werden die Positionen "angelegt"
        GridPane.setConstraints(userName, 1, 0);
        GridPane.setConstraints(labelPassword, 0, 1);
        GridPane.setConstraints(passwordUser, 1, 1);
        GridPane.setConstraints(loginStatus, 1, 2);
        GridPane.setConstraints(loginNow, 0, 3);
        GridPane.setConstraints(registerNow, 1, 3);

        grid.getChildren().addAll(labelUser, userName, labelPassword, passwordUser, loginStatus, loginNow, registerNow); //hier werden dem grid die buttons, textfelder und labels übergeben
    }

    public void updateStatusLabel(String result){

        loginStatus.setText("result");
    }

    public Scene getLoginScene() {

        return this.loginScene;
    }

    @Override
    public void setLoginPresenter(LoginPresenter loginPresenter) {
      this.loginPresenter = loginPresenter;
    }


}
