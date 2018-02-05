package ui.app.game.board.storage;

import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.control.Label;
import mvp.view.IView;

public interface IStorageView extends IView {

  void setUserColor(String color);

  ArrayList<Group> getStones();

  Label getPointsLabel();
}
