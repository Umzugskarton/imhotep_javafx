package ui.app.game.board.ship;

import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import mvp.view.IView;

public interface IShipView extends IView{
  ArrayList<Group> getStones();
  ArrayList<Rectangle> getColorStones();
}
