package ui.app.game.userinterface;

import static misc.language.TextBundle.getString;

import com.google.common.eventbus.EventBus;
import connection.Connection;
import data.lobby.CommonLobby;
import data.user.User;
import events.SiteType;
import helper.fxml.GenerateFXMLView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
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

  @FXML
  private ComboBox<String> selectCardBox;

  @FXML
  private Button getNewStonesButton;

  @FXML
  private Button moveShipToLocationButton;

  @FXML
  private Button placeStonesButton;

  @FXML
  private Button playCardButton;

  private final IGameView parentView;
  private final UserInterfacePresenter mainPresenter;
  private final EventBus eventBus;

  private final User user;
  private CommonLobby lobby;
  private int playerId;

  // Own Parent
  private Parent myParent;

  public UserInterfaceView(IGameView parentView, EventBus eventBus, Connection connection,
      User user, CommonLobby lobby, int playerId) {
    this.parentView = parentView;
    this.eventBus = eventBus;
    this.lobby = lobby;
    this.user = user;
    this.playerId = playerId;
    System.out.println("PlayerID ist " + playerId);
    this.mainPresenter = new UserInterfacePresenter(this, eventBus, connection, user, lobby);
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
          .loadView("/ui/fxml/app/game/board/UserInterfaceView.fxml", this, eventBus);
    }
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

  public Label getCurrentPlayerLabel() {
    return currentPlayerLabel;
  }

  public Rectangle getPlayerColorRectangle() {
    return this.playerColorRectangle;
  }

  public Label getUiBannerLabel() {
    return this.uiBannerLabel;
  }

  public Label getUiBannerSmallLabel() {
    return this.uiBannerSmallLabel;
  }

  public ComboBox<Integer> getSelectStoneLocationBox() {
    return selectStoneLocationBox;
  }

  public ComboBox<String> getSelectShipLocationBox() {
    return selectShipLocationBox;
  }

  public ComboBox<Integer> getSelectShipBox() { return selectShipBox; }

  public ComboBox<Integer> getSelectShipToLocationBox() { return selectShipToLocationBox; }

  public ComboBox<String> getSelectCardBox() { return selectCardBox; }

  public Button getGetNewStonesButton() { return getNewStonesButton; }

  public Button getMoveShipToLocationButton() {
    return moveShipToLocationButton;
  }

  public Button getPlaceStonesButton() {
    return placeStonesButton;
  }

  public Button getPlayCardButton() {
    return playCardButton;
  }

  public int getPlayerId() { return playerId; }

// TODO Moves richtig mit Parent ausführen

  @FXML
  public void sendFillUpStorageMove() {
    mainPresenter.sendFillUpStorageMove();
  }

  @FXML
  void setStoneLocationCBox(ActionEvent event) {
    if (selectShipToLocationBox.getValue() != null) {
      mainPresenter.setStoneLocationCBox(selectShipToLocationBox.getValue());
    }
  }

  public ArrayList<ComboBox<Integer>> getShipCBoxes() {
    ArrayList<ComboBox<Integer>> a = new ArrayList<>();
    Collections.addAll(a, selectShipBox, selectShipToLocationBox);
    return a;
  }

  @FXML
  void sendToolCardMove() {
    if(this.selectCardBox.getValue() != null) {
      this.mainPresenter.sendToolCardMove();
    }
  }

  @FXML
  void sendVoyageToStoneSiteMove() {
    HashMap<String, SiteType> findSiteType = new HashMap<>();
    findSiteType.put(getString("site." + SiteType.OBELISKS.name()), SiteType.OBELISKS);
    findSiteType.put(getString("site." + SiteType.MARKET.name()), SiteType.MARKET);
    findSiteType
        .put(getString("site." + SiteType.BURIALCHAMBER.name()), SiteType.BURIALCHAMBER);
    findSiteType.put(getString("site." + SiteType.PYRAMID.name()), SiteType.PYRAMID);
    findSiteType.put(getString("site." + SiteType.TEMPLE.name()), SiteType.TEMPLE);
    if (selectShipBox.getValue() != null && selectShipLocationBox.getValue() != null) {
      mainPresenter.sendVoyageToStoneSiteMove(selectShipBox.getValue(),
          findSiteType.get(selectShipLocationBox.getValue()));
    }
  }

  @FXML
  void sendLoadUpShipMove() {
    if (selectShipToLocationBox.getValue() != null && selectStoneLocationBox.getValue() != null) {
      mainPresenter.sendLoadUpShipMove(selectShipToLocationBox.getValue(),
          selectStoneLocationBox.getValue());
    }
  }

}
