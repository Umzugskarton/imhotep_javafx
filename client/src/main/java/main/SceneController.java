package main;

import requests.lobbylistRequest;
import board.presenter.BoardPresenter;
import board.view.BoardViewImplFx;
import com.google.common.eventbus.EventBus;
import games.presenter.GamesPresenter;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import login.presenter.LoginPresenter;
import login.view.LoginViewImpl;
import mainmenu.presenter.MainmenuPresenter;
import mainmenu.view.MainmenuViewImpl;
import registration.presenter.RegistrationPresenter;
import registration.view.RegistrationViewImpl;
import lobby.presenter.LobbyPresenter;
import lobby.view.LobbyViewImpl;
import socket.ClientSocket;

import java.io.IOException;

public class SceneController {

  private static final int STAGE_WIDTH = 720;
  private static final int STAGE_HEIGHT = 480;

  private Stage stage;
  private Stage gameStage;
  //Board
  private Parent boardRoot;

  // Socket
  private ClientSocket clientSocket;

  // Presenter
  private RegistrationPresenter registrationPresenter;
  private BoardPresenter boardPresenter;
  private LoginPresenter loginPresenter;
  private MainmenuPresenter MainmenuPresenter;
  private EventBus eventBus;
  private LobbyPresenter LobbyPresenter;
  private GamesPresenter gamesPresenter;


  public SceneController(Stage stage) {
    this.eventBus = new EventBus();
    this.eventBus.register(new EventListener(this));
    this.clientSocket = new ClientSocket(this, eventBus);
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

    this.clientSocket.send(new lobbylistRequest());
    this.stage.setScene(this.MainmenuPresenter.getMainmenuView().getMainmenuScene());
    this.stage.getScene().getStylesheets().add("style.css");
  }

  public void toLobbyScene() {
    //Es wird stets beim Wechsel zur LobbyScene eine neuer Presenter/View gesetzt
    this.LobbyPresenter = new LobbyPresenter(new LobbyViewImpl(), this);

    this.stage.setScene(this.LobbyPresenter.getLobbyView().getLobbyScene());
    this.stage.getScene().getStylesheets().add("style.css");
  }

  public void toBoardScene() {
    if (boardRoot == null) {
      //Nur für die Präsentation
      stage.close();
      gameStage = new Stage();
      gameStage.initStyle(StageStyle.DECORATED);
      gameStage.setTitle("Imhotep");
      gameStage.setHeight(907);
      gameStage.setWidth(1582);
      gameStage.show();
      try {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/BoardView.fxml"));
        boardRoot = loader.load();
        BoardViewImplFx view = loader.getController();
        boardPresenter = new BoardPresenter(view, this, getLobbyPresenter().getCLTLobby());
        view.setBoardPresenter(boardPresenter);
        Scene boardScene = new Scene(boardRoot);
        this.gameStage.setScene(boardScene); // nachher mit fxml wieder ändern
        gameStage.getScene().getStylesheets().add("style.css");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public ClientSocket getClientSocket() {
    return this.clientSocket;
  }

  public BoardPresenter getBoardPresenter() {
    return boardPresenter;
  }

  public LobbyPresenter getLobbyPresenter() {
    return this.LobbyPresenter;
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

  public void toggleFullscreen() {
    stage.setFullScreen(!stage.isFullScreen());
  }

}
