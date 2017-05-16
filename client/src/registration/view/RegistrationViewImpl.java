package registration.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import login.presenter.LoginPresenter;
import registration.presenter.RegistrationPresenter;


public class RegistrationViewImpl implements RegistrationView {
  private RegistrationPresenter registrationPresenter;
  private Scene registrationScene;
  private Label registrationStatus;

  public RegistrationViewImpl(){
    buildRegistration();
  }

  public void buildRegistration() {
    GridPane root = new GridPane();
    registrationScene = new Scene(root);

    Label name = new Label("Name:");
    TextField nameField = new TextField();
    nameField.setPromptText("Username eingeben");

    Label password = new Label("Password:");
    PasswordField passwordField = new PasswordField();
    passwordField.setPromptText("Passwort eingeben");

    Label password2 = new Label("Repeat password:");
    PasswordField passwordField2 = new PasswordField();
    passwordField2.setPromptText("Passwort wiederholen");

    Label email = new Label("Email:");
    TextField emailField = new TextField();
    emailField.setPromptText("E-Mail eingeben");

    registrationStatus = new Label();

    Button register = new Button("Register");
    register.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){
        public void handle(ActionEvent event){
          if(passwordField.getText().equals(passwordField2.getText())) {
            registrationPresenter.register(nameField.getText(), passwordField.getText(), emailField.getText());
          } else {

          }
      }
    });

    Button login = new Button("Back to Login");
    login.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){
      public void handle(ActionEvent event){
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

  public void updateStatusLabel(String result){
    registrationStatus.setText("result");
  }

  public Scene getRegistrationScene() {
      return this.registrationScene;
  }

  public void setRegistrationPresenter(RegistrationPresenter registrationPresenter) {
    this.registrationPresenter = registrationPresenter;
  }
}
