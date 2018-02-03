package ui.app.game.board.sites;

import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import mvp.view.IView;

import java.util.ArrayList;

public interface ISiteView extends IView{
  ArrayList<Group> getStones();
  Rectangle getColorStones(int i);
}
