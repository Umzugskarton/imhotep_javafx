package board.view;


import board.presenter.BoardPresenter;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

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
  private Polygon deko0;
  @FXML
  private Polygon deko1;
  @FXML
  private ImageView deko2;
  @FXML
  private ImageView deko3;
  @FXML
  private Polygon[] dekos = {deko0, deko1};




  public void addHouse(int i, String s){
    dekos[i].setFill(Color.web(s));
    Image image = new Image("haus.png");
    ImageView house = new ImageView();
    house.setFitHeight(120);
    house.setFitWidth(250);
    house.setImage(image);
    houses.add(house, 0, i );
  }

  public void setBoardPresenter(BoardPresenter boardPresenter) {
    this.boardPresenter = boardPresenter;
  }

  public void full(){
    boardPresenter.fullscreen();
  }
}
