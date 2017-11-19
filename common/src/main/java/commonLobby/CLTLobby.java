package commonLobby;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;

public class CLTLobby {

  private int lobbyID;
  private String name;
  private String host;
  private boolean ishost;
  private int size;
  private boolean hasPW;
  private int usercount;
  private String belegung;
  private ArrayList<LobbyUser> users;
  private ArrayList<String> colors;
  private boolean[] ready;

  public CLTLobby(int lobbyID, String name, ArrayList<LobbyUser> users, boolean hasPW, int size,
      boolean ishost, String host, boolean[] ready, ArrayList<String> colors) {
    this.lobbyID = lobbyID;
    this.name = name;
    this.users = users;
    this.hasPW = hasPW;
    this.size = size;
    this.usercount = users.size();
    this.ishost = ishost;
    this.host = host;
    this.ready = ready;
    this.colors = colors;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public void setLobbyID(int id) {
    this.lobbyID = id;
  }

  public void setUsers(ArrayList<LobbyUser> newUsers, boolean[] ready, ArrayList<String> colors) {
    users.clear();
    users.addAll(newUsers);
    this.colors = colors;
    this.ready = ready;
  }

  public ArrayList<LobbyUser> getUsers() {
    return users;
  }

  public LobbyUser getUserbyName(String name) {
    int i =0;
    LobbyUser e= null;
    while(users.get(i).getUsername()!=name && i<users.size())
      e=users.get(i);
    return e;
  }

  public LobbyUser getUserbyLobbyId(int id) {
    return users.get(id);
  }


  public String getName() {
    return this.name;
  }

  public int getSize() {
    return this.size;
  }

  public boolean hasPW() {
    return this.hasPW;
  }

  public int getUsercount() {
    return this.usercount;
  }

  public String getBelegung() {
    return this.belegung;
  }

  public void setBelegung(int usercount) {
    this.belegung = (usercount + " / " + size);
  }

  public boolean isHost() {
    return this.ishost;
  }

  public String getHost() {
    return this.host;
  }

  public boolean[] getReady() {
    return ready;
  }

  public ArrayList<String> getColors() {
    return colors;
  }

  public void setColors(ArrayList<String> colors) {
    this.colors = colors;
  }

  public void setUsers(ArrayList<LobbyUser> users) {
    this.users.addAll(users);
  }

  public void setLobbyId(int lobbyId) {
    this.lobbyID = lobbyId;
  }


  public int getLobbyId() {
    return lobbyID;
  }


  public void setReady(boolean[] ready) {
    this.ready = ready;
  }


  public ObservableList<LobbyUser> getObservableUsers() {
    ObservableList<LobbyUser> x = FXCollections.observableArrayList();
    x.addAll(this.users);
    return x;
  }

  public ObservableList<LobbyUser> getObservableColors() {
    ObservableList<LobbyUser> y = FXCollections.observableArrayList();
    y.addAll(this.users);
    return y;
  }
}
