package board.view;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Collections;

public class BurialChamberViewImplFx {

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
  @FXML
  private Group stone15;
  @FXML
  private Group stone16;
  @FXML
  private Group stone17;
  @FXML
  private Group stone18;
  @FXML
  private Group stone19;
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
  private Group stone26;


  public ArrayList<Group> getStones() {
    ArrayList<Group> a = new ArrayList<>();
    Collections
        .addAll(a, stone0, stone1, stone2, stone3, stone4, stone5, stone6, stone7, stone8, stone9,
            stone10, stone11, stone12, stone13, stone14, stone15, stone16, stone17, stone18,
            stone18, stone19, stone20, stone21, stone22, stone23, stone24, stone25, stone26);
    return a;
  }

  public Rectangle getColorStones(int i) {
    return (Rectangle) getStones().get(i).getChildren().get(0);
  }
}
