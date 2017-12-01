package board.view;


import board.presenter.BoardPresenter;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;


import java.awt.*;

public class BoardViewImplFx {

  private BoardPresenter boardPresenter;

  @FXML
  private Pane mainframe;

  @FXML
  private Button fb;

  @FXML
  private GridPane houses;

  @FXML
  private GridPane[] storages = new GridPane[4];


  public void addHouse(String s){
    Image image = new Image(s);
    ImageView house = new ImageView();
    house.setFitHeight(50);
    house.setFitWidth(50);
    house.setImage(image);
    houses.getChildren().add(house);
  }
}
