package lobby.view;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.TextFlow;
import lobby.presenter.LobbyPresenter;

public interface LobbyView {

  Scene getLobbyScene();

  void setLobbyPresenter(LobbyPresenter lobbyPresenter);

  void initLobbyInfo();

  void updateTable();

  TextFlow getChatText();

}
