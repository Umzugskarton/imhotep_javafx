package games.model;

import commonLobby.CLTLobby;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GameListImpl implements GameList {

  private ObservableList<CLTLobby> games;

  public GameListImpl() {
    games = FXCollections.observableArrayList();  //Liste zum Speichern der Lobbies
  }

  public ObservableList<CLTLobby> getGames() {
    return this.games;
  }
}
