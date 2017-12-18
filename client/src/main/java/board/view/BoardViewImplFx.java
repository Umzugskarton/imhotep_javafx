package board.view;

import board.presenter.BoardPresenter;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.control.Label;

import java.awt.*;
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
  private Pane userInterfacePane;

  @FXML
  public Label roundLabel;

  @FXML
  public Label currentPlayerLabel;

  @FXML
  public Label turnTimerLabel;

  @FXML
  private Button getNewStonesButton;

  @FXML
  private Button moveShipToLocationButton;

  @FXML
  private Button placeStonesButton;

  @FXML
  private Button playCardButton;

  @FXML
  private ComboBox selectShipBox;

  @FXML
  private ComboBox selectShipLocationBox;

  @FXML
  private ComboBox selectShipToLocationBox;

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

  public GridPane getHouses() {
    return houses;
  }

  public Pane getUserInterface() {
    return userInterfacePane;
  }

  public void addHouse(int i, AnchorPane house){
    Text text = new Text("x: " + i);
    this.houses.add(house, 0, i );
  }

  public Label getTurnTimerLabel() { return this.turnTimerLabel; }

  public Label getRoundLabel() { return this.roundLabel; }

  public Label getCurrentPlayerLabel() { return this.currentPlayerLabel; }

  public void setBoardPresenter(BoardPresenter boardPresenter) {
    this.boardPresenter = boardPresenter;
  }

  public void full(){
    boardPresenter.fullscreen();
  }
}
