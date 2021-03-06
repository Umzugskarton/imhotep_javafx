package ui.app.lobby;

import com.google.common.eventbus.EventBus;
import connection.Connection;
import data.lobby.CommonLobby;
import data.user.User;
import helper.fxml.GenerateFXMLView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import mvp.view.INavigateableView;
import mvp.view.ShowViewEvent;
import ui.app.lobby.chat.ChatView;
import ui.app.lobby.control.LobbyControlView;
import ui.app.lobby.usertable.UserTableView;
import ui.dialog.IDialogView;

public class LobbyView implements ILobbyView {

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private AnchorPane lobbyViewRoot;

  @FXML
  private Pane dialogBackground;

  @FXML
  private Pane dialog;

  @FXML
  private Pane subParentLobbyChat;

  @FXML
  private Pane subParentLobbyUserList;

  @FXML
  private Pane subParentLobbyControl;

  private final INavigateableView parentView;
  private final LobbyPresenter mainPresenter;
  private final EventBus eventBus;

  // Own Parent
  private Parent myParent;

  // Subviews
  private ChatView chatView;
  private UserTableView userTableView;
  private LobbyControlView lobbyControlView;

  private final User user;

  public LobbyView(INavigateableView parentView, EventBus eventBus, Connection connection,
      User user, CommonLobby lobby) {
    this.parentView = parentView;
    this.eventBus = eventBus;
    this.user = user;
    this.mainPresenter = new LobbyPresenter(this, eventBus, connection, user, lobby);
    bind();
    initOwnView();
  }

  private void bind() {
    eventBus.register(this);
  }

  @Override
  public void initOwnView() {
    if (this.myParent == null) {
      this.myParent = GenerateFXMLView.getINSTANCE()
          .loadView("/ui/fxml/app/lobby/LobbyView.fxml", this, eventBus);
    }
  }

  @FXML
  void initialize() {
    this.chatView = new ChatView(this, eventBus, mainPresenter.getClientSocket(),
        mainPresenter.getLobby(), user);
    this.lobbyControlView = new LobbyControlView(this, this.eventBus,
        mainPresenter.getClientSocket(), mainPresenter.getLobby(), user);
    this.userTableView = new UserTableView(this, this.chatView, this.mainPresenter.getLobby(),
        this.lobbyControlView, eventBus, mainPresenter.getClientSocket(), user);

    setSubParentLobbyChat(this.chatView.getRootParent());
    setSubParentLobbyUserList(this.userTableView.getRootParent());
    setSubParentControl(this.lobbyControlView.getRootParent());
  }

  public void setSubParentLobbyChat(Parent subParent) {
    this.subParentLobbyChat.getChildren().clear();
    this.subParentLobbyChat.getChildren().add(subParent);
  }

  public void setSubParentLobbyUserList(Parent subParent) {
    this.subParentLobbyUserList.getChildren().clear();
    this.subParentLobbyUserList.getChildren().add(subParent);
  }


  public void setSubParentControl(Parent subParent) {
    this.subParentLobbyControl.getChildren().clear();
    this.subParentLobbyControl.getChildren().add(subParent);
  }


  @Override
  public INavigateableView getParentView() {
    //return AppView
    return this.parentView;
  }

  //TODO Main-Tab in AppView soll angezeigt werden durch diese Methode.
  @Override
  public ShowViewEvent getEventToShowThisView() {
    return null;
  }

  @Override
  public String getTitle() {
    return "Lobby";
  }

  @Override
  public Parent getRootParent() {
    return this.myParent;
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

  public void updateUserTableView(CommonLobby lobby) {
    this.userTableView.updateLobby(lobby);
  }
}
