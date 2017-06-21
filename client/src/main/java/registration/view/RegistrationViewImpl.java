package registration.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import registration.presenter.RegistrationPresenter;


public class RegistrationViewImpl implements RegistrationView {

  private RegistrationPresenter registrationPresenter;
  private Scene registrationScene;
  private Label registrationStatus;

  public RegistrationViewImpl() {
    buildRegistration();
  }

  public void buildRegistration() {
    GridPane root = new GridPane();
    root.setAlignment(Pos.CENTER);
    root.setPadding(new Insets(5));
    root.setHgap(5);
    root.setVgap(5);
    registrationScene = new Scene(root);

    Label name = new Label("Benutzername:");
    TextField nameField = new TextField();
    nameField.setPromptText("Benutzernamen eingeben");

    Label password = new Label("Passwort:");
    PasswordField passwordField = new PasswordField();
    passwordField.setPromptText("Passwort eingeben");

    Label password2 = new Label("Passwort wdh.:");
    PasswordField passwordField2 = new PasswordField();
    passwordField2.setPromptText("Passwort wiederholen");

    Label email = new Label("Email:");
    TextField emailField = new TextField();
    emailField.setPromptText("E-Mail eingeben");

    registrationStatus = new Label();
    registrationStatus.setTextFill(Color.RED);

    Button register = new Button("Registrieren");
    register.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
      public void handle(ActionEvent event) {
        String password1 = passwordField.getText();
        String password2 = passwordField2.getText();
        String username = nameField.getText();
        String email = emailField.getText();

        registrationPresenter.sendRegisterRequest(username, password1, password2, email);
      }
    });

    Button login = new Button("Zum Login wechseln");
    login.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
      public void handle(ActionEvent event) {
        registrationPresenter.toLoginScene();
      }
    });

    root.add(name, 2, 1);
    root.add(nameField, 3, 1);
    root.add(password, 2, 2);
    root.add(passwordField, 3, 2);
    root.add(password2, 2, 3);
    root.add(passwordField2, 3, 3);
    root.add(email, 2, 4);
    root.add(emailField, 3, 4);
    root.add(registrationStatus, 3, 5);
    root.add(register, 2, 6);
    root.add(login, 3, 6);
  }

  public void updateStatusLabel(String result) {
    registrationStatus.setText(result);
  }

  public Scene getRegistrationScene() {
    return this.registrationScene;
  }

  public void setRegistrationPresenter(RegistrationPresenter registrationPresenter) {
    this.registrationPresenter = registrationPresenter;
  }
}
