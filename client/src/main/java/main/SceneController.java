package main;

import com.google.common.eventbus.EventBus;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lobby.view.LobbyViewImpl;
import login.presenter.LoginPresenter;
import login.view.LoginViewImplFx;
import mainmenu.presenter.MainmenuPresenter;
import mainmenu.view.MainmenuViewImplFx;
import registration.presenter.RegistrationPresenter;
import lobby.presenter.LobbyPresenter;
import registration.view.RegistrationViewImplFx;
import socket.ClientSocket;

import java.io.IOException;

public class SceneController {

  private static final int STAGE_WIDTH = 720;
  private static final int STAGE_HEIGHT = 480;

  private Stage stage;
  private Scene scene;

  // Socket
  private ClientSocket clientSocket;

  // Roots
  private Parent loginRoot;
  private Parent registrationRoot;
  private Parent menuRoot;
  private Parent lobbyRoot;

  // Presenter
  private RegistrationPresenter registrationPresenter;
  private LoginPresenter loginPresenter;
  private MainmenuPresenter mainmenuPresenter;
  private EventBus eventBus;
  private LobbyPresenter lobbyPresenter;

  public SceneController(Stage stage) {
    this.eventBus = new EventBus();
    this.eventBus.register(new EventListener(this));
    this.clientSocket = new ClientSocket(this, eventBus);

    this.stage = stage;
    this.scene = new Scene(new Group());

    this.toLoginScene();
    stage.setScene(scene);

    stage.setTitle("Imhotep");
    stage.setHeight(STAGE_HEIGHT);
    stage.setWidth(STAGE_WIDTH);
    stage.setResizable(false);
    scene.getStylesheets().add("style.css");
    stage.show();
  }

  public void toRegistrationScene() {
    if (registrationRoot == null) {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RegistrationView.fxml"));
      try {
        registrationRoot = loader.load();
      } catch (IOException e) {
        e.printStackTrace();
      }
      RegistrationViewImplFx view = loader.<RegistrationViewImplFx>getController();
      this.registrationPresenter = new RegistrationPresenter(view, this);
    }
    scene.setRoot(registrationRoot);
  }

  public void toLoginScene() {
    if (loginRoot == null) {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LoginViewImplFx.fxml"));
      try {
        loginRoot = loader.load();
      } catch (IOException e) {
        e.printStackTrace();
      }
      LoginViewImplFx view = loader.<LoginViewImplFx>getController();
      this.loginPresenter = new LoginPresenter(view, this);
    }
    scene.setRoot(loginRoot);
  }

  public void toMainmenuScene() {
    if (menuRoot == null) {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainmenuViewFXML.fxml"));
      try {
        menuRoot = loader.load();
      } catch (IOException e) {
        e.printStackTrace();
      }
      MainmenuViewImplFx view = loader.<MainmenuViewImplFx>getController();
      view.setSceneController(this);
      this.mainmenuPresenter = new MainmenuPresenter(view, this);
    }

    scene.setRoot(menuRoot);
  }

  public void toLobbyScene() {
    if (this.lobbyPresenter == null) {
      this.lobbyPresenter = new LobbyPresenter(new LobbyViewImpl(), this);
    }

    this.stage.setScene(this.lobbyPresenter.getLobbyView().getLobbyScene());
    this.stage.getScene().getStylesheets().add("style.css");
  }

  public ClientSocket getClientSocket() {
    return this.clientSocket;
  }

  public LobbyPresenter getLobbyPresenter() {
    return this.lobbyPresenter;
  }

  public LoginPresenter getLoginPresenter() {
    return this.loginPresenter;
  }

  public MainmenuPresenter getMainmenuPresenter() {
    return this.mainmenuPresenter;
  }

  public RegistrationPresenter getRegistrationPresenter() {
    return this.registrationPresenter;
  }

  public Stage getStage() {
    return this.stage;
  }
}
