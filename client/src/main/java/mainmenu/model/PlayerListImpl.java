package mainmenu.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PlayerListImpl implements PlayerList{

  private ObservableList<String> players;

  public PlayerListImpl(){
    players = FXCollections.observableArrayList();  //Liste zum Speichern der eingeloggten Spieler
  }

  public ObservableList<String> getPlayers(){
    return this.players;
  }
}
