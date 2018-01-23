package board.view;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import java.util.ArrayList;
import java.util.Collections;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class ShipViewImplFx {

  @FXML
  private Group stone0;

  @FXML
  private Group stone1;

  @FXML
  private Group stone2;

  @FXML
  private Group stone3;

  @FXML
  private Pane sprite;


  public ArrayList<Group> getStones() {
    ArrayList<Group> a = new ArrayList<>();
    Collections.addAll(a, stone0, stone1, stone2, stone3);
    return a;
  }

  public ArrayList<Rectangle> getColorStones() {
    ArrayList<Rectangle> a = new ArrayList<>();
    for (Group g : getStones()) {
      int i = 0;
      for (Node x : g.getChildren()) {
        if (i == 0) {
          a.add((Rectangle) x);
        }
        i++;
      }
    }
    return a;
  }

  public Pane getSprite() {
    return sprite;
  }


}
