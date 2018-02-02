package ui.app.game.board;

import java.util.ArrayList;
import javafx.scene.control.ProgressBar;
import mvp.view.IView;
import ui.app.game.board.ship.ShipView;
import ui.app.game.board.storage.StorageView;

public interface IBoardView extends IView {
  ProgressBar getTurnTimerProgress();
  ArrayList<StorageView> getStorageViews();
  ArrayList<ShipView> getShipViews();
  void setShips(ArrayList<int[]> ships);
}
