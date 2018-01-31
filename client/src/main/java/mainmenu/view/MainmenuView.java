package mainmenu.view;

import chat.view.ChatView;
import create.view.CreateView;
import games.view.GamesView;
import javafx.scene.Scene;
import mainmenu.presenter.MainmenuPresenter;
import profile.view.ProfileView;

public interface MainmenuView {

  void buildMainmenu();

  void setMainmenuPresenter(MainmenuPresenter mainmenuPresenter);

  Scene getMainmenuScene();

  void initChat(ChatView chatView);

  void initCreate(CreateView createView);

  void initGames(GamesView gamesView);

  void initProfile(ProfileView profileView);

  void initPlayerList();

  void openModal(String msg);
}