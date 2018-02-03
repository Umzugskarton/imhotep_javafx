package ui.app.game.userinterface;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import mvp.view.IView;

import java.util.ArrayList;

public interface IUserInterfaceView extends IView {

  Label getCurrentPlayerLabel();

  Rectangle getPlayerColorRectangle();

  ComboBox<Integer> getSelectStoneLocationBox();

  ComboBox<String> getSelectShipLocationBox();

  Label getUiBannerLabel();

  Label getUiBannerSmallLabel();

  GridPane getUserInterface();

  GridPane getHoldingArea();

  ArrayList<ComboBox<Integer>> getShipCBoxes();
}
