package board.view;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created on 20.12.2017.
 */
public class TempleViewImplFx {
  @FXML
  private Group stone0;
  @FXML
  private Group stone1;
  @FXML
  private Group stone2;
  @FXML
  private Group stone3;
  @FXML
  private Group stone4;
  @FXML
  private Group stone5;
  @FXML
  private Group stone6;
  @FXML
  private Group stone7;
  @FXML
  private Group stone8;
  @FXML
  private Group stone9;
  @FXML
  private Group stone10;
  @FXML
  private Group stone11;
  @FXML
  private Group stone12;
  @FXML
  private Group stone13;
  @FXML
  private Group stone14;

  public ArrayList<Group> getStones(){
    ArrayList<Group> a = new ArrayList<>();
    Collections.addAll(a, stone0,stone1,stone2,stone3,stone4,stone5,stone6,stone7,stone8,stone9,stone10,stone11,stone12,stone13, stone14);
    return a;
  }

  public Rectangle getColorStones(int i){
    return (Rectangle) getStones().get(i).getChildren().get(0);
  }


}
