package mainmenu.view;

import javafx.collections.ObservableList;
import javafx.scene.Scene;
import mainmenu.presenter.MainmenuPresenter;

public interface MainmenuView {

  void buildMainmenu();

  void setMainmenuPresenter(MainmenuPresenter mainmenuPresenter);

  Scene getMainmenuScene();

  void initPlayerList();
}
