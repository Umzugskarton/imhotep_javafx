package main;

import registration.presenter.RegistrationPresenter;
import registration.view.RegistrationViewImpl;

import javafx.stage.Stage;
import login.presenter.LoginPresenter;
import login.view.LoginViewImpl;
import socket.ClientSocket;

public class SceneController {

  private static final int STAGE_WIDTH = 640;
  private static final int STAGE_HEIGHT = 250;

  private Stage stage;

  // Socket
  private ClientSocket clientSocket;

  // Presenter
  private RegistrationPresenter registrationPresenter;
  private LoginPresenter loginPresenter;

  public SceneController(Stage stage) {
    this.clientSocket = new ClientSocket(this);
    this.stage = stage;
    this.toLoginScene();
    stage.setTitle("Imhotep");
    stage.setHeight(STAGE_HEIGHT);
    stage.setWidth(STAGE_WIDTH);
    stage.show();
  }

  public void toRegistrationScene() {
    if (this.registrationPresenter == null) {
      this.registrationPresenter = new RegistrationPresenter(new RegistrationViewImpl(), this);
    }
    this.stage.setScene(this.registrationPresenter.getRegistrationView().getRegistrationScene());
  }

  public void toLoginScene() {
    if (this.loginPresenter == null) {
      this.loginPresenter = new LoginPresenter(new LoginViewImpl(), this);
    }

    this.stage.setScene(this.loginPresenter.getLoginView().getLoginScene());
  }

  public ClientSocket getClientSocket() {
    return this.clientSocket;
  }

  public LoginPresenter getLoginPresenter() {
    return this.loginPresenter;
  }

  public RegistrationPresenter getRegistrationPresenter() {
    return this.registrationPresenter;
  }
}
