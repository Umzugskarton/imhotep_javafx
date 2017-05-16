package main;

import registration.presenter.RegistrationPresenter;
import registration.view.RegistrationViewImpl;

import javafx.stage.Stage;
import login.presenter.LoginPresenter;
import login.view.LoginViewImpl;
import socket.ClientSocket;

public class SceneController {
    private Stage stage;

    // Socket
    private ClientSocket clientSocket;

    // Presenter
    private RegistrationPresenter registrationPresenter;
    private LoginPresenter loginPresenter;

    private final int stageWidth = 350; //maße des fensters
    private final int stageHeight = 250;

    public SceneController(Stage stage){
        this.clientSocket = new ClientSocket(this);
        this.stage = stage;

        this.toLoginScene();

        stage.setTitle("Imhotep");
        stage.setHeight(this.stageHeight);
        stage.setWidth(this.stageWidth);
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

  public LoginPresenter getLoginPresenter() { return this.loginPresenter; }

  public RegistrationPresenter getRegistrationPresenter() { return this.registrationPresenter; }
}
