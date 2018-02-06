package ui.app.lobby.control;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import connection.Connection;
import data.lobby.CommonLobby;
import data.user.User;
import events.app.lobby.LobbyInfoEvent;
import helper.fxml.GenerateFXMLView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import mvp.view.INavigateableView;
import ui.app.lobby.usertable.UserTableView;


public class LobbyControlView implements ILobbyControlView {

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private GridPane rootParent;

  @FXML
  private Label userSizeLabel;

  @FXML
  Button setReadyButton;

  @FXML
  Button startGameButton;

  @FXML
  Button changeColorButton;

  @FXML
  private Text actiontarget;

  private Parent myParent = rootParent;

  private final INavigateableView parentView;
  private final LobbyControlPresenter presenter;
  private final EventBus eventBus;

  public LobbyControlView(INavigateableView parentView, EventBus eventBus, Connection clientSocket,
                          CommonLobby lobby, User user) {
    this.parentView = parentView;
    this.presenter = new LobbyControlPresenter(this, eventBus, clientSocket, lobby, user);
    this.eventBus = eventBus;
    this.eventBus.register(this);
    initOwnView();
  }

  @Override
  public void initOwnView() {
    if (this.myParent == null) {
      this.myParent = GenerateFXMLView.getINSTANCE()
          .loadView("/ui/fxml/app/lobby/control/LobbyControlView.fxml", this, eventBus);
    }

    this.showStartGameButton(this.presenter.isLobbyHost());
    this.updateUserSizeLabel(this.presenter.getUserSize());
  }

  @Override
  public Parent getRootParent() {
    return this.myParent;
  }

  @FXML
  private void handlesetReadyButtonAction(ActionEvent event) {
    clearStatusLabel();
    presenter.sendSetReadyRequest();
  }

  @FXML
  private void handlestartGameButtonAction(ActionEvent event) {
    presenter.startGame();
  }

  @FXML
  private void handlechangeColorButtonAction(ActionEvent event) {
    clearStatusLabel();
    presenter.sendChangeColorRequest();
  }

  public Label getUserSizeLabel() {
    return userSizeLabel;
  }

  @Override
  public void updateStatusLabel(String m) {
    this.actiontarget.setText(m);
  }

  @Override
  public void showStartGameButton(boolean show) {
    this.startGameButton.setVisible(show);
  }

  @Override
  public void clearStatusLabel(){
    this.actiontarget.setText("");
  }

  @Override
  public void updateUserSizeLabel(String m) {
    this.userSizeLabel.setText("Belegung: " + m);
  }

  public Button getStartGameButton() { return this.startGameButton; }

  public Button getSetReadyButton() {
    return setReadyButton;
  }

  public Button getChangeColorButton() {
    return changeColorButton;
  }
}