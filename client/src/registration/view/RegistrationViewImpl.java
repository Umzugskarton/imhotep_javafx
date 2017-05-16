package registration.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import json.ClientCommands;
import org.json.simple.JSONObject;
import registration.presenter.RegistrationPresenter;
import registration.view.RegistrationView;
import main.SceneController;


public class RegistrationViewImpl implements RegistrationView {

    private Scene registrationScene;
    private RegistrationPresenter registrationPresenter;
    private Label registrationStatus;

    public RegistrationViewImpl(){
        buildRegistration();
    }

    @Override
    public void buildRegistration() {

        GridPane grid = new GridPane();
        registrationScene = new Scene(grid);

        Label labelUser = new Label("Benutzername: "); //Label und Textfelder für den Benutzer
        TextField userName = new TextField();
        userName.setPromptText("Benutzernamen eingeben");

        Label labelPassword = new Label("Passwort: "); //Label und Textfelder für das Passwort
        PasswordField passwordUser = new PasswordField();
        passwordUser.setPromptText("Passwort eingeben");

        Label labelPasswordRepeat = new Label("Passwort wiederholen: "); //Label und Textfelder für das Passwort
        PasswordField passwordRepeat = new PasswordField();
        passwordRepeat.setPromptText("Passwort erneut eingeben");

        Label labelEmail = new Label("Email: "); //Label und Textfelder für die Email
        TextField userEmail = new TextField();
        userEmail.setPromptText("Email eingeben");

        registrationStatus = new Label();    //gibt an, ob Registrierung erfolgreich war

        Button registerNow = new Button("Register"); //Buttons werden angelegt
        registerNow.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event){
                JSONObject registerCommand = new ClientCommands().registerCommand(userName.getText(), passwordUser.getText(), userEmail.getText());
                registrationPresenter.getSceneController().getClientSocket().send(registerCommand);
            }
        });

        Button loginNow = new Button("Login");

        loginNow.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event){
                registrationPresenter.getSceneController().toLoginScene();
            }
        });


        GridPane.setConstraints(labelUser, 0, 0); //Hier werden die Positionen "angelegt"
        GridPane.setConstraints(userName, 1, 0);
        GridPane.setConstraints(labelPassword, 0, 1);
        GridPane.setConstraints(passwordUser, 1, 1);
        GridPane.setConstraints(labelPasswordRepeat,  0, 2);
        GridPane.setConstraints(passwordRepeat,  1, 2);
        GridPane.setConstraints(labelEmail,  0, 3);
        GridPane.setConstraints(userEmail,  1, 3);
        GridPane.setConstraints(registrationStatus, 0, 4);
        GridPane.setConstraints(registerNow, 0, 5);
        GridPane.setConstraints(loginNow, 1, 5);

        grid.getChildren().addAll(labelUser, userName, labelPassword, passwordUser, passwordRepeat, userEmail, registrationStatus, registerNow, loginNow, labelEmail); //hier werden dem grid die buttons, textfelder und labels übergeben
    }


    public void updateStatusLabel(String result){
        registrationStatus.setText("result");
    }

    public Scene getRegistrationScene() {
        return this.registrationScene;
    }

    @Override
    public void setRegistrationPresenter(RegistrationPresenter registrationPresenter) {
        this.registrationPresenter = registrationPresenter;
    }


}
