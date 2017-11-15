package lobby.view;

import javafx.scene.Scene;
import lobby.presenter.LobbyPresenter;

public interface LobbyView {

  Scene getLobbyScene();

  void setLobbyPresenter(LobbyPresenter lobbyPresenter);

  void initLobbyInfo();

  void updateColorRectangle();
}
