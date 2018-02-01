package games.model;

import data.lobby.CommonLobby;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GameListImpl implements GameList {

  private ObservableList<CommonLobby> games;

  public GameListImpl() {
    games = FXCollections.observableArrayList();  //Liste zum Speichern der Lobbies
  }

  public ObservableList<CommonLobby> getGames() {
    return this.games;
  }
}
