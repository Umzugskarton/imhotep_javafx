package ui.app.game.board.storage;


import javafx.scene.Group;
import javafx.scene.control.Label;
import mvp.view.IView;

import java.util.ArrayList;

public interface IStorageView extends IView {
  void setUserColor(String color);
  ArrayList<Group> getStones();
  Label getPointsLabel();
}
