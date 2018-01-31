package ui.app.game.board;

import javafx.scene.control.ProgressBar;
import mvp.view.INavigateableSubView;
import ui.app.game.board.ship.ShipView;
import ui.app.game.board.storage.StorageView;

import java.util.ArrayList;

public interface IBoardView extends INavigateableSubView {
  ProgressBar getTurnTimerProgress();
  ArrayList<StorageView> getStorageViews();
  ArrayList<ShipView> getShipViews();
  void setShips(ArrayList<int[]> ships);
}
