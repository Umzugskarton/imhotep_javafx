package login.view;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import login.presenter.LoginPresenter;


public class LoginViewImplFx {

  private LoginPresenter loginPresenter;

  @FXML
  private TextField nameField1;

  @FXML
  private PasswordField passwordField2;

  @FXML
  private Label statusLabel;

  @FXML
  void register1_handle(ActionEvent event) {
    loginPresenter.toRegisterScene();
  }

  @FXML
  void login1_handle(ActionEvent event) {
    String password = passwordField2.getText();
    String username = nameField1.getText();

    loginPresenter.sendLoginRequest(username, password);
  }

  public void updateStatusLabel(String result) {
    statusLabel.setText(result);
  }

  public void setLoginPresenter(LoginPresenter loginPresenter) {
    this.loginPresenter = loginPresenter;
  }
}

