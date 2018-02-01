package games.model;

import javafx.collections.ObservableList;
import data.lobby.CommonLobby;

public interface GameList {

  ObservableList<CommonLobby> getGames();

}
