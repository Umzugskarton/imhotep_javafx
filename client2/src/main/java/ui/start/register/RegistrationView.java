package ui.start.register;

import com.google.common.eventbus.EventBus;
import connection.Connection;
import helper.fxml.GenerateFXMLView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import mvp.view.INavigateableView;
import mvp.view.ShowViewEvent;
import ui.dialog.IDialogView;
import ui.start.login.ShowLoginViewEvent;

public class RegistrationView implements IRegistrationView {

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private GridPane rootParent;

  @FXML
  private TextField userField;

  @FXML
  private PasswordField passwordField;

  @FXML
  private PasswordField passwordRepeatField;

  @FXML
  private TextField emailField;

  @FXML
  private Label actiontarget;

  @FXML
  Button registerButton;

  private Parent myParent = rootParent;

  private final INavigateableView parentView;

  private final RegistrationPresenter registrationPresenter;
  private final EventBus eventBus;

  public RegistrationView(INavigateableView parentView, EventBus eventBus, Connection connection) {
    this.parentView = parentView;
    this.registrationPresenter = new RegistrationPresenter(this, eventBus, connection);
    this.eventBus = eventBus;
    initOwnView();
  }

  @Override
  public void initOwnView() {
    if (this.myParent == null) {
      this.myParent = GenerateFXMLView.getINSTANCE()
          .loadView("/ui/fxml/start/register/RegistrationView.fxml", this, eventBus);
    }
  }

  @FXML
  void initialize() {
  }

  @FXML
  void handleToLoginButtonAction(ActionEvent event) {
    eventBus.post(new ShowLoginViewEvent());

  }

  @FXML
  void handleRegisterButtonAction(ActionEvent event) {
    registrationPresenter.sendRegisterRequest(
        userField.getText(), passwordField.getText(), passwordRepeatField.getText(),
        emailField.getText());
  }

  @FXML
  private void handlePressedKeyAction(KeyEvent event) {
    if (KeyCode.ENTER == event.getCode()) {
      registerButton.fire();
    }
  }

  @Override
  public void updateStatusLabel(String result) {
    actiontarget.setText(result);
  }

  @Override
  public INavigateableView getParentView() {
    return this.parentView;
  }

  @Override
  public String getTitle() {
    return "Register";
  }

  @Override
  public ShowViewEvent getEventToShowThisView() {
    return new ShowRegisterViewEvent();
  }

  @Override
  public void clearForm() {
    userField.setText("");
    passwordField.setText("");
    passwordRepeatField.setText("");
    emailField.setText("");
  }

  @Override
  public void clearPasswordField() {
    passwordField.setText("");
    passwordRepeatField.setText("");
  }

  @Override
  public Parent getRootParent() {
    return this.myParent;
  }

  public void setNameField(String nameField) {
    this.userField.setText(nameField);
  }

  public void setPasswordField(String passwordField) {
    this.passwordField.setText(passwordField);
  }


  @Override
  public void hideDialog() {

  }

  @Override
  public void showDialog(IDialogView view) {

  }
}
