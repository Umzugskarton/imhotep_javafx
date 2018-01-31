package SRVevents;

import commonLobby.CLTLobby;
import java.util.ArrayList;

public class lobbylistEvent implements Event {

  private String event = "lobbylist";
  private ArrayList<CLTLobby> lobbies = new ArrayList<>();

  public String getEvent() {
    return this.event;
  }

  public void setLobbies(ArrayList<CLTLobby> lobbies) {
    this.lobbies.addAll(lobbies);
  }

  public ArrayList<CLTLobby> getLobbies() {
    return this.lobbies;
  }
}
