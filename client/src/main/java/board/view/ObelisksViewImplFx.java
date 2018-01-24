package board.view;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Collections;

public class ObelisksViewImplFx {

  @FXML
  private Group stone00;
  @FXML
  private Group stone01;
  @FXML
  private Group stone02;
  @FXML
  private Group stone03;
  @FXML
  private Group stone04;
  @FXML
  private Group stone05;
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
  @FXML
  private Group stone15;
  @FXML
  private Group stone20;
  @FXML
  private Group stone21;
  @FXML
  private Group stone22;
  @FXML
  private Group stone23;
  @FXML
  private Group stone24;
  @FXML
  private Group stone25;
  @FXML
  private Group stone30;
  @FXML
  private Group stone31;
  @FXML
  private Group stone32;
  @FXML
  private Group stone33;
  @FXML
  private Group stone34;
  @FXML
  private Group stone35;

  public ArrayList<Group> getStones() {
    ArrayList<Group> a = new ArrayList<>();

    Collections.addAll(a,
        stone00, stone01, stone02, stone03, stone04, stone05, stone10, stone11,
        stone12, stone13, stone14, stone15, stone20, stone21, stone22, stone23, stone24,
        stone25, stone30, stone31, stone32, stone33, stone34, stone35);
    return a;
  }

  public Rectangle getColorStones(int i) {
    return (Rectangle) getStones().get(i).getChildren().get(0);
  }
}
