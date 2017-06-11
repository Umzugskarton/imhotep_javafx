package mainmenu.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by Matthias on 03.06.2017.
 */
public class PlayerListImpl implements PlayerList{

  ObservableList<String> players;

  public PlayerListImpl(){
    players = FXCollections.observableArrayList();  //Liste zum Speichern der eingeloggten Spieler
  }

  public ObservableList<String> getPlayers(){
    return this.players;
  }
}
