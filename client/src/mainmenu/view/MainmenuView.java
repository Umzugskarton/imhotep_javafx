package mainmenu.view;

import javafx.collections.ObservableList;
import javafx.scene.Scene;
import mainmenu.presenter.MainmenuPresenter;

/**
 * Created by mirco, kristin on 28.05.2017.
 */
public interface MainmenuView {

  void buildMainmenu();

  void setMainmenuPresenter(MainmenuPresenter mainmenuPresenter);

  Scene getMainmenuScene();

  ObservableList<String> getPlayers();
}
