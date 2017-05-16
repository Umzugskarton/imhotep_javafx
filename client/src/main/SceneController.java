package main;

import registration.presenter.RegistrationPresenter;
import registration.view.RegistrationViewImpl;

import javafx.stage.Stage;
import login.presenter.LoginPresenter;
import login.view.LoginViewImpl;

public class SceneController {
    private Stage stage;
    private RegistrationPresenter registrationPresenter;
    private LoginPresenter loginPresenter;

    private final int stageWidth = 350; //maße des fensters
    private final int stageHeight = 250;


    public SceneController(Stage stage){
        this.stage = stage;
        loginPresenter = new LoginPresenter(new LoginViewImpl(), this);
        stage.setScene(loginPresenter.getLoginView().getLoginScene()); //hier wird die szene übergeben
        stage.setTitle("Imhotep"); //title des fensters
        stage.setHeight(stageHeight);
        stage.setWidth(stageWidth);
        stage.show(); //zeigt das fenster an
    }

    public void toRegistrationScene() {
      if (registrationPresenter == null) {
        registrationPresenter = new RegistrationPresenter(new RegistrationViewImpl(), this);
      }
      stage.setScene(registrationPresenter.getRegistrationView().getRegistrationScene());
    }

  public void toLoginScene() {
    if (loginPresenter == null) {
      loginPresenter = new LoginPresenter(new LoginViewImpl(), this);
    }
    stage.setScene(loginPresenter.getLoginView().getLoginScene());
  }
}
