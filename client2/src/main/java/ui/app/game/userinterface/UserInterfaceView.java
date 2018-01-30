package ui.app.game.userinterface;

import com.google.common.eventbus.EventBus;
import connection.Connection;
import data.lobby.Lobby;
import data.user.User;
import helper.fxml.GenerateFXMLView;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import mvp.view.INavigateableView;
import mvp.view.ShowViewEvent;


public class UserInterfaceView implements IUserInterfaceView {

  // User Interface
  @FXML
  private GridPane userInterfacePane;

  @FXML
  public Label uiBannerLabel;

  @FXML
  public Label uiBannerSmallLabel;

  @FXML
  public Label roundLabel;

  @FXML
  public Label currentPlayerLabel;

  @FXML
  public ProgressBar turnTimerProgress;

  @FXML
  public Rectangle playerColorRectangle;


  private final INavigateableView parentView;
  private final UserInterfacePresenter mainPresenter;
  private final EventBus eventBus;

  private final User user;
  private Lobby lobby;

  // Own Parent
  private Parent myParent;

  public UserInterfaceView(INavigateableView parentView, EventBus eventBus, Connection connection, User user, Lobby lobby){
    this.parentView = parentView;
    this.eventBus = eventBus;
    this.user = user;
    this.mainPresenter = new UserInterfacePresenter(this, eventBus, connection, user, lobby);
    bind();
    initOwnView();
  }

  private void bind(){
    eventBus.register(this);
  }

  @Override
  public void initOwnView() {
    if(this.myParent == null)
      this.myParent = GenerateFXMLView.getINSTANCE().loadView("/ui/fxml/app/main/game/UserInterfaceView.fxml", this, eventBus);
  }

  @Override
  public INavigateableView getParentView() {
    return null;
  }

  @Override
  public ShowViewEvent getEventToShowThisView() {
    return null;
  }

  @Override
  public String getTitle() {
    return "Board";
  }

  @Override
  public Parent getRootParent() {
    return null;
  }

  public ProgressBar getTurnTimerProgress() { return this.turnTimerProgress; }

  public Label getRoundLabel() { return this.roundLabel; }

  public Label getCurrentPlayerLabel() { return this.currentPlayerLabel; }

  public Rectangle getPlayerColorRectangle() { return this.playerColorRectangle; }

  public Label getUiBannerLabel() { return this.uiBannerLabel; }

  public Label getUiBannerSmallLabel() { return this.uiBannerSmallLabel; }

}
