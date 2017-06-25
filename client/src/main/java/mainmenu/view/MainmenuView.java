package mainmenu.view;

import chat.view.ChatView;
import javafx.scene.Scene;
import mainmenu.presenter.MainmenuPresenter;
import profile.view.ProfileView;

public interface MainmenuView {

    void buildMainmenu();

    void setMainmenuPresenter(MainmenuPresenter mainmenuPresenter);

    Scene getMainmenuScene();

    void initChat(ChatView chatView);

    void initProfile(ProfileView profileView);

    void initPlayerList();
}
