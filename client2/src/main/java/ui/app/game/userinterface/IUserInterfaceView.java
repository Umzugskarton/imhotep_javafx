package ui.app.game.userinterface;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import mvp.view.INavigateableSubView;
import mvp.view.IView;

public interface IUserInterfaceView extends IView {

  Label getCurrentPlayerLabel();

  Rectangle getPlayerColorRectangle();

  ComboBox<Integer> getSelectStoneLocationBox();

  ComboBox<String> getSelectShipLocationBox();

  Label getUiBannerLabel();

  Label getUiBannerSmallLabel();

  GridPane getUserInterface();

  GridPane getHoldingArea();
}
