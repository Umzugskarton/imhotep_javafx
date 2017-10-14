package games.model;

import javafx.collections.ObservableList;
import lobby.model.CLTLobby;

public interface GameList {
    ObservableList<CLTLobby> getGames();
}
