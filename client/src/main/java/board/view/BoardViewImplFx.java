package board.view;

import board.presenter.BoardPresenter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.Collections;

public class BoardViewImplFx {

  private BoardPresenter boardPresenter;

  @FXML
  private AnchorPane boardView;

  @FXML
  private GridPane houses;

  @FXML
  private GridPane[] storages = new GridPane[4];

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

  @FXML
  private Button getNewStonesButton;

  @FXML
  private Button moveShipToLocationButton;

  @FXML
  private Button placeStonesButton;

  @FXML
  private Button playCardButton;

  @FXML
  private ComboBox<Integer> selectShipBox;

  @FXML
  private ComboBox selectShipLocationBox;

  @FXML
  private ComboBox<Integer> selectShipToLocationBox;

  @FXML
  private ComboBox<Integer> selectStoneLocationBox;

  @FXML
  private Pane berth0;

  @FXML
  private Pane berth1;

  @FXML
  private Pane berth2;

  @FXML
  private Pane berth3;

  @FXML
  private Pane marketPier;

  @FXML
  private Pane burialChamberPier;

  @FXML
  private Pane templePier;

  @FXML
  private Pane obelisksPier;

  @FXML
  private Pane pyramidsPier;

  @FXML
  private GridPane holdingArea;

  @FXML
  public void sendFillUpStorageMove(ActionEvent e) {
      this.boardPresenter.sendFillUpStorageMove();
  }

  public GridPane getHouses() {
    return houses;
  }

  public GridPane getUserInterface() {
    return userInterfacePane;
  }

  public GridPane getHoldingArea() {
    return holdingArea;
  }

  public ComboBox<Integer> getSelectStoneLocationBox() {
    return selectStoneLocationBox;
  }



  public ArrayList<ComboBox<Integer>> getShipCBoxes(){
    ArrayList<ComboBox<Integer>> a = new ArrayList<>();
    Collections.addAll(a, selectShipBox, selectShipToLocationBox);
    return a;
  }

  public void addHouse(int i, AnchorPane house){
    Text text = new Text("x: " + i);
    this.houses.add(house, 0, i );
  }

  public ComboBox getSelectShipLocationBox() {
    return selectShipLocationBox;
  }

  @FXML
  void setStoneLocationCBox(ActionEvent event){
    if ( selectShipToLocationBox.getValue() != null){
      boardPresenter.setStoneLocationCBox(selectShipToLocationBox.getValue());
    }
    else {
      System.out.println("Null");
    }
  }

  @FXML
  void sendLoadUpShipMove(){
    if (selectShipToLocationBox.getValue() != null && selectStoneLocationBox != null){
      boardPresenter.sendLoadUpShipMove(selectShipToLocationBox.getValue(), selectStoneLocationBox.getValue());
    }
  }
  public ProgressBar getTurnTimerProgress() { return this.turnTimerProgress; }

  public Label getRoundLabel() { return this.roundLabel; }

  public Label getCurrentPlayerLabel() { return this.currentPlayerLabel; }

  public Rectangle getPlayerColorRectangle() { return this.playerColorRectangle; }

  public Label getUiBannerLabel() { return this.uiBannerLabel; }

  public Label getUiBannerSmallLabel() { return this.uiBannerSmallLabel; }

  public void setBoardPresenter(BoardPresenter boardPresenter) {
    this.boardPresenter = boardPresenter;
  }

  public void full(){
    boardPresenter.fullscreen();
  }

  public void setGuiToFront(){
    userInterfacePane.toFront();
  }
  public ArrayList<Pane> getBerths(){
    ArrayList<Pane> a = new ArrayList<>();
    Collections.addAll(a, berth0, berth1, berth2,berth3);
    return a;
  }

  public void addShip(int i, AnchorPane ship){
      getBerths().get(i).getChildren().add(ship);
  }
}
