package ui.app.game.userinterface;

import com.google.common.eventbus.EventBus;
import connection.Connection;
import data.lobby.Lobby;
import data.user.User;
import helper.fxml.GenerateFXMLView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import mvp.view.ShowViewEvent;
import ui.app.game.IGameView;


public class UserInterfaceView implements IUserInterfaceView {

  @FXML
  private GridPane userInterfacePane;

  @FXML
  public Label uiBannerLabel;

  @FXML
  public Label uiBannerSmallLabel;

  @FXML
  public Label currentPlayerLabel;

  @FXML
  public Rectangle playerColorRectangle;

  @FXML
  private GridPane holdingArea;

  @FXML
  private ComboBox<Integer> selectShipBox;

  @FXML
  private ComboBox<String> selectShipLocationBox;

  @FXML
  private ComboBox<Integer> selectShipToLocationBox;

  @FXML
  private ComboBox<Integer> selectStoneLocationBox;


  private final IGameView parentView;
  private final UserInterfacePresenter mainPresenter;
  private final EventBus eventBus;

  private final User user;
  private Lobby lobby;

  // Own Parent
  private Parent myParent;

  public UserInterfaceView(IGameView parentView, EventBus eventBus, Connection connection, User user, Lobby lobby){
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
      this.myParent = GenerateFXMLView.getINSTANCE().loadView("/ui/fxml/app/game/UserInterfaceView.fxml", this, eventBus);
  }

  @Override
  public IGameView getParentView() {
    return parentView;
  }

  @Override
  public ShowViewEvent getEventToShowThisView() {
    return null;
  }

  @Override
  public String getTitle() {
    return "UserInterface";
  }

  @Override
  public Parent getRootParent() {
    return myParent;
  }

  public GridPane getUserInterface() {
    return userInterfacePane;
  }

  public GridPane getHoldingArea() {
    return holdingArea;
  }

  public Label getCurrentPlayerLabel() { return this.currentPlayerLabel; }

  public Rectangle getPlayerColorRectangle() { return this.playerColorRectangle; }

  public Label getUiBannerLabel() { return this.uiBannerLabel; }

  public Label getUiBannerSmallLabel() { return this.uiBannerSmallLabel; }

  public ComboBox<Integer> getSelectStoneLocationBox() {
    return selectStoneLocationBox;
  }

  public ComboBox<String> getSelectShipLocationBox(){
    return selectShipLocationBox;
  }

// TODO Moves richtig mit Parent ausführen

  @FXML
  public void sendFillUpStorageMove(ActionEvent e) {
    mainPresenter.sendFillUpStorageMove();
  }

  @FXML
  void setStoneLocationCBox(ActionEvent event){
    if ( selectShipToLocationBox.getValue() != null){
      mainPresenter.setStoneLocationCBox(selectShipToLocationBox.getValue());
    }
    else {
      System.out.println("Null");
    }
  }

  @FXML
  void sendVoyageToStoneSiteMove(){
    if (selectShipBox.getValue() != null && selectShipLocationBox.getValue() != null)
      mainPresenter.sendVoyageToStoneSiteMove(selectShipBox.getValue(),selectShipLocationBox.getValue());
    else
      System.out.println("A: " +selectShipBox.getValue() + " B: " + selectShipLocationBox.getValue());
  }

  @FXML
  void sendLoadUpShipMove(){
    if (selectShipToLocationBox.getValue() != null && selectStoneLocationBox != null){
      mainPresenter.sendLoadUpShipMove(selectShipToLocationBox.getValue(), selectStoneLocationBox.getValue());
    }
  }

}