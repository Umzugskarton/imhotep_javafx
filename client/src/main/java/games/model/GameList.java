package games.model;

import javafx.collections.ObservableList;
import org.json.simple.JSONArray;

/**
 * Created by fabia on 29.07.2017.
 */
public interface GameList {
    ObservableList<Lobby> getGames();
}
