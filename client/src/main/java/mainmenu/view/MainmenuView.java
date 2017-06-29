package mainmenu.view;

import chat.view.ChatView;
import javafx.scene.Scene;
import mainmenu.presenter.MainmenuPresenter;

public interface MainmenuView {

  void buildMainmenu();

  void setMainmenuPresenter(MainmenuPresenter mainmenuPresenter);

  Scene getMainmenuScene();

  void initChat(ChatView chatView);

  void initPlayerList();
}
