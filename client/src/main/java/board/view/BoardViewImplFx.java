package board.view;


import board.presenter.BoardPresenter;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.awt.*;

public class BoardViewImplFx {

  private BoardPresenter boardPresenter;

  @FXML
  private AnchorPane BoardView;

  @FXML
  private Pane mainframe;

  @FXML
  private Button fb;

  @FXML
  private GridPane houses;

  @FXML
  private GridPane[] storages = new GridPane[4];
  @FXML
  private Button getNewStones;

  @FXML
  private Button selectShipStones;
  @FXML
  private Button placeStones;

  @FXML
  private Button SelectShipLocation;

  @FXML
  private Button moveShipToLocation;
  @FXML
  private Button playCard;

  @FXML
  private Pane berth0;

  @FXML
  private Pane berth1;

  @FXML
  private Pane berth2;

  @FXML
  private Pane berth3;

  @FXML
  private Pane marketpier;

  @FXML
  private Pane burialchamberpier;

  @FXML
  private Pane templepier;

  @FXML
  private Pane obeliskspier;

  @FXML
  private Pane pyramidspier;

  @FXML
  private TextFlow gamelog;

  public GridPane getHouses() {
    return houses;
  }

  public void addHouse(int i, AnchorPane house){
    Text text = new Text("x: " + i);
    gamelog.getChildren().addAll();
    houses.add(house, 0, i );
  }

  public TextFlow getGamelog() {
    return gamelog;
  }

  public void setBoardPresenter(BoardPresenter boardPresenter) {
    this.boardPresenter = boardPresenter;
  }

  public void full(){
    boardPresenter.fullscreen();
  }
}
