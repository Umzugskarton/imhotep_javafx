package ui.app.game.board.sites;

import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import mvp.view.IView;

public interface ISiteView extends IView{
  ArrayList<Group> getStones();
  Rectangle getColorStones(int i);
}
