package ui.app.profil;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import connection.Connection;
import data.user.User;
import helper.fxml.GenerateFXMLView;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import mvp.view.INavigateableView;
import mvp.view.ShowViewEvent;
import ui.app.main.chat.ChatView;
import ui.app.main.lobbylist.LobbyTableView;
import ui.app.main.userlist.UserListView;
import ui.app.profil.profilchange.ProfilChangeView;
import ui.app.profil.settings.ProfilSettingView;
import ui.dialog.DialogView;
import ui.dialog.IDialogView;
import ui.dialog.lobby.createlobby.CreateLobbyView;
import ui.dialog.lobby.createlobby.ShowCreateLobbyDialogEvent;
import ui.dialog.lobby.joinlobby.JoinLobbyView;
import ui.dialog.lobby.joinlobby.ShowJoinLobbyDialogEvent;
import ui.dialog.misc.IDialogableView;
import ui.dialog.misc.ViewIdentifier;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfilView implements IProfilView, IDialogableView {

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private AnchorPane profilViewRoot;

  @FXML
  private Pane dialogBackground;

  @FXML
  private Pane dialog;

  @FXML
  private Pane subParentLeftTop;

  @FXML
  private Pane subParentLeftBottom;

  @FXML
  private Pane subParentRightTop;

  @FXML
  private Pane subParentRightBottom;

  @FXML
  private Pane popupPane;

  private final INavigateableView parentView;
  private final ProfilPresenter presenter;
  private final EventBus eventBus;

  // Own Parent
  private Parent myParent;

  // Subviews
  private ProfilChangeView profilChangeView;
  private ProfilSettingView profilSettingView;

  //Dialog
  private DialogView dialogView;

  private final User user;

  public ProfilView(INavigateableView parentView, EventBus eventBus, Connection connection, User user) {
    this.parentView = parentView;
    this.eventBus = eventBus;
    this.user = user;
    this.presenter = new ProfilPresenter(this, eventBus, connection, user);
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
          .loadView("/ui/fxml/app/profil/ProfilView.fxml", this, eventBus);
    }
  }

  @FXML
  private void initialize() {
    this.profilChangeView = new ProfilChangeView(this, eventBus, presenter.getConnection(), user);
    //this.profilSettingView = new ProfilSettingView(this, eventBus, presenter.getConnection(), user);

    setSubParentLeftTop(this.profilChangeView.getRootParent());
    //setSubParentRightTop(this.profilSettingView.getRootParent());

    this.dialogView = new DialogView(this, this.eventBus);
    this.dialog.getChildren().add(this.dialogView.getRootParent());
  }

  public void setSubParentLeftTop(Parent subParent) {
    this.subParentLeftTop.getChildren().clear();
    this.subParentLeftTop.getChildren().add(subParent);
  }

  public void setSubParentLeftBottom(Parent subParent) {
    this.subParentLeftBottom.getChildren().clear();
    this.subParentLeftBottom.getChildren().add(subParent);
  }

  public void setSubParentRightTop(Parent subParent) {
    this.subParentRightTop.getChildren().clear();
    this.subParentRightTop.getChildren().add(subParent);
  }

  public void setSubParentRightBottom(Parent subParent) {
    this.subParentRightBottom.getChildren().clear();
    this.subParentRightBottom.getChildren().add(subParent);
  }

  @Override
  public INavigateableView getParentView() {
    return this.parentView;
  }

  @Override
  public ShowViewEvent getEventToShowThisView() {
    return null;
  }


  @Override
  public String getTitle() {
    return "Profil";
  }

  @Override
  public Parent getRootParent() {
    return this.myParent;
  }


  @Override
  public void showDialog(IDialogView view) {
    this.dialogView.showDialog(view);
    dialogBackground.toFront();
    dialogBackground.setVisible(true);
  }

  @Override
  public void hideDialog() {
    dialogBackground.toBack();
    dialogBackground.setVisible(false);
  }
}