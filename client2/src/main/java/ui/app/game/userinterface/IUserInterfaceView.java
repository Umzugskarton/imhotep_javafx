package ui.app.game.userinterface;

import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
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

  ArrayList<ComboBox<Integer>> getShipCBoxes();

  ComboBox<String> getSelectCardBox();

  Button getGetNewStonesButton();

  Button getMoveShipToLocationButton();

  Button getPlaceStonesButton();

  Button getPlayCardButton();

  ComboBox<Integer> getSelectShipBox();

  ComboBox<Integer> getSelectShipToLocationBox();

  int getPlayerId();
}
