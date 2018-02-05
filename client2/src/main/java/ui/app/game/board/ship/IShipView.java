package ui.app.game.board.ship;

import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import mvp.view.IView;

import java.util.ArrayList;

public interface IShipView extends IView {
    ArrayList<Group> getStones();

    ArrayList<Rectangle> getColorStones();
}
