package ui.app;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import connection.Connection;
import data.lobby.CommonLobby;
import data.user.User;
import events.app.chat.ChatMessageEvent;
import events.app.chat.WhisperChatEvent;
import events.app.game.GameEvent;
import events.app.game.StartGameEvent;
import events.app.lobby.LobbyInfoEvent;
import helper.fxml.GenerateFXMLView;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.MenuButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import mvp.view.ShowViewEvent;
import requests.lobby.LeaveLobbyRequest;
import requests.main.LogoutRequest;
import ui.app.game.GameView;
import ui.app.lobby.LobbyView;
import ui.app.main.MainView;
import ui.app.profil.ProfilView;
import ui.app.profil.ShowProfilViewEvent;
import ui.dialog.DialogView;
import ui.dialog.IDialogView;
import ui.layout.StageLayout;
import ui.start.ShowStartViewEvent;

public class AppView implements IAppView {

  @FXML
  private ResourceBundle resource;

  @FXML
  private URL location;

  @FXML
  private AnchorPane appViewRoot;

  @FXML
  private Pane dialogBackground;

  @FXML
  private Pane dialog;

  @FXML
  private HBox navigation;

  @FXML
  private MenuButton appViewMenuButton;

  @FXML
  private TabPane appViewMainTabPane;

  @FXML
  private Tab appViewMainTab;

  @FXML
  private Pane appViewBottomPane;


  private final AppPresenter presenter;
  private final EventBus eventBus;
  private final User user;
  private ArrayList<CommonLobby> lobbies = new ArrayList<>();

  // Own Parent
  private Parent myParent;

  //TabSubViews
  private MainView mainView;
  private ArrayList<LobbyView> lobbyViews = new ArrayList<>();
  private ArrayList<GameView> gameViews = new ArrayList<>();
  Map<Integer, EventBus> gameEventbuses = new HashMap<>();
  private ProfilView profilView;
  private Tab profilTab;

  //PopupView
  private DialogView popupView;

  public AppView(EventBus eventBus, Connection connection, User user, StageLayout stageLayout) {
    this.eventBus = eventBus;
    this.user = user;
    this.presenter = new AppPresenter(this, eventBus, connection);
    bind();
    initOwnView();
    stageLayout.configNavigation(this.navigation, true);
  }

  private void bind() {
    eventBus.register(this);
  }

  @Override
  public void initOwnView() {
    if (this.myParent == null) {
      this.myParent = GenerateFXMLView.getINSTANCE()
          .loadView("/ui/fxml/app/AppView.fxml", this, eventBus);
    }
  }

  @FXML
  private void initialize() {
    this.mainView = new MainView(this, eventBus, this.presenter.getConnection(), this.user);
    Pane mainViewPane = (Pane) this.appViewMainTab.getContent();
    mainViewPane.getChildren().add(this.mainView.getRootParent());
  }

  @FXML
  private void handleYourProfilClick(ActionEvent event) {
    this.eventBus.post(new ShowProfilViewEvent());
  }

  @FXML
  private void handleLogoutClick(ActionEvent event){
    this.presenter.getConnection().send(new LogoutRequest());
  }

  public Tab addTab(LobbyView lobbyView, CommonLobby lobby) {
    Tab tab = new Tab();
    tab.setText("Lobby #" + lobby.getLobbyId());
    tab.setContent(lobbyView.getRootParent());
    tab.setId("lobbyTab");
    lobby.setMyTab(tab);
    tab.setOnClosed((event) -> presenter.getConnection().send(new LeaveLobbyRequest(lobby.getLobbyId())));
    this.appViewMainTabPane.getTabs().add(tab);
    return tab;
  }

  @Override
  public ShowViewEvent getEventToShowThisView() {
    return new ShowStartViewEvent();
  }

  @Override
  public String getTitle() {
    return "";
  }

  @Override
  public Parent getRootParent() {
    return this.myParent;
  }

  @Subscribe
  public void onLobbyJoinSuccessfulEvent(LobbyInfoEvent e) {
    boolean found = false;
    for (CommonLobby l : lobbies) {
      if (l.getLobbyId() == e.getLobby().getLobbyId()) {
        found = true;
        l.setUsers(e.getLobby().getUsers(), e.getLobby().getReady(), e.getLobby().getColors());
        break;
      }
    }
    if (!found) {
      CommonLobby lobby = e.getLobby();
      lobbies.add(lobby);
      EventBus gameEventbus = new EventBus();
      gameEventbuses.put(lobby.getLobbyId(), gameEventbus);
      LobbyView lobbyView = new LobbyView(this, this.eventBus, this.presenter.getConnection(),
          this.user, lobby);
      Tab tab = addTab(lobbyView, lobby);
      lobbyViews.add(lobbyView);

      lobbyView.updateUserTableView(lobby);
      this.appViewMainTabPane.getSelectionModel().select(tab);
    }
  }

  @Subscribe
  public void onShowProfilEvent(ShowProfilViewEvent e) {
    if(this.profilView == null) {
      this.profilView = new ProfilView(this,this.eventBus,this.presenter.getConnection(),this.user);
    profilTab = new Tab();
    profilTab.setText("Profil");
    profilTab.setContent(profilView.getRootParent());
    profilTab.setId("profil");

    profilTab.setOnCloseRequest(new EventHandler<Event>() {
      @Override
      public void handle(Event event) {
        profilTab = null;
        profilView = null;
      }
    });
    this.appViewMainTabPane.getTabs().add(profilTab);
    this.appViewMainTabPane.getSelectionModel().select(profilTab);
    } else {
      this.appViewMainTabPane.getSelectionModel().select(profilTab);
    }
  }

  @Subscribe
  public void onStartGameEvent(StartGameEvent e) {
    CommonLobby lobby = null;
    for (CommonLobby l : lobbies) {
      if (l.getLobbyId() == e.getLobbyId()) {
        lobby = l;
        break;
      }
    }

    GameView gameView = new GameView(this, gameEventbuses.get(e.getLobbyId()),
        this.presenter.getConnection(), this.user, lobby);
    Tab tab = lobby.getMyTab();
    tab.setText("Game #" + lobby.getLobbyId());
    tab.setContent(gameView.getRootParent());
    tab.setId("gameTab");
    gameViews.add(gameView);
    this.appViewMainTabPane.getSelectionModel().select(0);
    this.appViewMainTabPane.getSelectionModel().select(tab);
  }

  public void showDialog(IDialogView view) {
    dialogBackground.toFront();
    dialogBackground.setVisible(true);
    dialog.getChildren().add(view.getRootParent());
  }

  public void hideDialog() {
    dialogBackground.toBack();
    dialogBackground.setVisible(false);
    dialog.getChildren().clear();
  }

  @Subscribe
  public void onGameEvent(GameEvent event) {
    gameEventbuses.get(event.getLobbyId()).post(event);
  }

  @Subscribe
  public void onChatMessageEvent(ChatMessageEvent event) {
    this.gameEventbuses.get(event.getLobbyId()).post(event);
  }

  @Subscribe
  public void onWhisperChatEvent(WhisperChatEvent event) {
    this.gameEventbuses.get(event.getLobbyId()).post(event);
  }
}

