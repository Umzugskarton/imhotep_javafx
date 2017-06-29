package main;

import javafx.stage.StageStyle;
import mainmenu.presenter.MainmenuPresenter;
import mainmenu.view.MainmenuViewImpl;
import registration.presenter.RegistrationPresenter;
import registration.view.RegistrationViewImpl;

import javafx.stage.Stage;
import login.presenter.LoginPresenter;
import login.view.LoginViewImpl;
import socket.ClientSocket;


public class SceneController {

  private static final int STAGE_WIDTH = 720;
  private static final int STAGE_HEIGHT = 480;

  private Stage stage;

  // Socket
  private ClientSocket clientSocket;

  // Presenter
  private RegistrationPresenter registrationPresenter;
  private LoginPresenter loginPresenter;
  private MainmenuPresenter MainmenuPresenter;

  public SceneController(Stage stage) {
    this.clientSocket = new ClientSocket(this);
    this.stage = stage;
    this.toLoginScene();
    stage.initStyle(StageStyle.TRANSPARENT);
    stage.setTitle("Imhotep");
    stage.setHeight(STAGE_HEIGHT);
    stage.setWidth(STAGE_WIDTH);
    stage.getScene().getStylesheets().add("style.css");
    stage.show();
  }

  public void toRegistrationScene() {
    if (this.registrationPresenter == null) {
      this.registrationPresenter = new RegistrationPresenter(new RegistrationViewImpl(), this);
    }
    this.stage.setScene(this.registrationPresenter.getRegistrationView().getRegistrationScene());
    this.stage.getScene().getStylesheets().add("style.css");
  }

  public void toLoginScene() {
    if (this.loginPresenter == null) {
      this.loginPresenter = new LoginPresenter(new LoginViewImpl(), this);
    }

    this.stage.setScene(this.loginPresenter.getLoginView().getLoginScene());
    this.stage.getScene().getStylesheets().add("style.css");
  }

  public void toMainmenuScene() {
    if (this.MainmenuPresenter == null) {
      this.MainmenuPresenter = new MainmenuPresenter(new MainmenuViewImpl(), this);
    }

    this.stage.setScene(this.MainmenuPresenter.getMainmenuView().getMainmenuScene());
    this.stage.getScene().getStylesheets().add("style.css");
  }

  public ClientSocket getClientSocket() {
    return this.clientSocket;
  }

  public LoginPresenter getLoginPresenter() {
    return this.loginPresenter;
  }

  public MainmenuPresenter getMainmenuPresenter() {
    return this.MainmenuPresenter;
  }

  public Stage getStage() {
    return this.stage;
  }

  public RegistrationPresenter getRegistrationPresenter() {
    return this.registrationPresenter;
  }
}
