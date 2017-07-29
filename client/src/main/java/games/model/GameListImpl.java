package games.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.simple.JSONArray;


public class GameListImpl implements GameList {
    private ObservableList<Lobby> games;

    public GameListImpl() {
        games = FXCollections.observableArrayList();  //Liste zum Speichern der Lobbies
    }

    public ObservableList<Lobby> getGames() {
        return this.games;
    }
}
