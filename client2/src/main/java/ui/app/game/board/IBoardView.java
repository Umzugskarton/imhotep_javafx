package ui.app.game.board;

import events.SiteType;
import java.util.ArrayList;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import mvp.view.IView;
import ui.app.game.board.ship.ShipView;
import ui.app.game.board.storage.StorageView;

public interface IBoardView extends IView {

  ProgressBar getTurnTimerProgress();

  ArrayList<StorageView> getStorageViews();

  ArrayList<ShipView> getShipViews();

  void setShips(ArrayList<int[]> ships);

  AnchorPane removeShipPaneById(int id);

  Pane getPierByType(SiteType type);
}
