package main;

import registration.model.RegistrationModelImpl;
import registration.presenter.RegistrationPresenter;
import registration.view.RegistrationViewImpl;

import javafx.stage.Stage;
import login.model.LoginModelImpl;
import login.presenter.LoginPresenter;
import login.view.LoginViewImpl;
import socket.ClientSocket;

public class SceneController {
    private Stage stage;
    private RegistrationPresenter registrationPresenter;
    private LoginPresenter loginPresenter;
    private ClientSocket clientSocket;

    // Fenstermaße
    private final int stageWidth = 350;
    private final int stageHeight = 250;

    public SceneController(Stage stage){
        this.clientSocket = new ClientSocket();;
        this.stage = stage;
        this.loginPresenter = new LoginPresenter(new LoginViewImpl(), new LoginModelImpl(), this);
        stage.setScene(loginPresenter.getLoginView().getLoginScene()); //hier wird die szene übergeben
        stage.setTitle("Imhotep"); // Fenstertitel
        stage.setHeight(stageHeight);
        stage.setWidth(stageWidth);
        stage.show();
    }

   public ClientSocket getClientSocket(){return this.clientSocket;}

    public void toRegistrationScene() {
      if (registrationPresenter == null) {
        registrationPresenter = new RegistrationPresenter(new RegistrationViewImpl(),
            new RegistrationModelImpl(), this);
      }
      stage.setScene(registrationPresenter.getRegistrationView().getRegistrationScene());
    }

  public void toLoginScene() {
    if (loginPresenter == null) {
      loginPresenter = new LoginPresenter(new LoginViewImpl(), new LoginModelImpl(), this);
    }
    stage.setScene(loginPresenter.getLoginView().getLoginScene());
  }
}
