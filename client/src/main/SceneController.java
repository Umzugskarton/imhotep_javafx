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
  //  private RegistrationPresenter registrationPresenter;
    private final int stageWidth = 350; //maße des fensters
    private final int stageHeight = 250;


    public SceneController(Stage stage, ClientSocket clientSocket){
        this.clientSocket = clientSocket;
        this.stage = stage;
        loginPresenter = new LoginPresenter(new LoginViewImpl(), new LoginModelImpl(), this);
        stage.setScene(loginPresenter.getLoginView().getLoginScene()); //hier wird die szene übergeben
        stage.setTitle("Imhotep"); //title des fensters
        stage.setHeight(stageHeight);
        stage.setWidth(stageWidth);
        stage.show(); //zeigt das fenster an
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
