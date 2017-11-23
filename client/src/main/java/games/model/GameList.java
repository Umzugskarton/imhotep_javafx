package games.model;

import javafx.collections.ObservableList;
import commonLobby.CLTLobby;

public interface GameList {

  ObservableList<CLTLobby> getGames();

}
