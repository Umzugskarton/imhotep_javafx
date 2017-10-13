package games.model;

import javafx.collections.ObservableList;
import lobby.model.Lobby;

public interface GameList {
    ObservableList<Lobby> getGames();
}
