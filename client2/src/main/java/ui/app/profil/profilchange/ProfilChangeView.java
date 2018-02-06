package ui.app.profil.profilchange;

import com.google.common.eventbus.EventBus;
import connection.Connection;
import data.lobby.CommonLobby;
import data.user.User;
import helper.fxml.GenerateFXMLView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import mvp.view.INavigateableView;
import ui.start.login.ShowLoginViewEvent;

import java.net.URL;
import java.util.ResourceBundle;


public class ProfilChangeView implements IProfilChangeView {

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

  private Parent myParent = rootParent;

  private final INavigateableView parentView;
  private final ProfilChangePresenter presenter;
  private final EventBus eventBus;

  public ProfilChangeView(INavigateableView parentView, EventBus eventBus, Connection clientSocket, User user) {
    this.parentView = parentView;
    this.presenter = new ProfilChangePresenter(this, eventBus, clientSocket, user);
    this.eventBus = eventBus;
    this.eventBus.register(this);
    initOwnView();
  }

  @Override
  public void initOwnView() {
    if (this.myParent == null) {
      this.myParent = GenerateFXMLView.getINSTANCE()
          .loadView("/ui/fxml/app/profil/profilchange/ProfilChangeView.fxml", this, eventBus);
    }
  }

  @Override
  public Parent getRootParent() {
    return this.myParent;
  }

  @FXML
  void handleChangeEMailButtonAction(ActionEvent event) {
    this.presenter.sendChangeProfilEMail(this.emailField.getText());
  }

  @FXML
  void handlechangePasswortButtonAction(ActionEvent event) {
    System.out.println("Push handlechangePasswortButtonAction");
    this.presenter.sendChangeProfilPasswort(this.passwordField.getText(), this.passwordRepeatField.getText());
  }

  @Override
  public void updateStatusLabel(String m) {
    this.actiontarget.setText(m);
  }

  @Override
  public void clearForm(){
    this.passwordRepeatField.setText("");
    this.passwordField.setText("");
    this.emailField.setText("");
  }

}