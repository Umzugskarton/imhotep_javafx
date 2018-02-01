package events.app.lobby;

import events.Event;

public class SetReadyToPlayEvent extends Event {

  private boolean[] readyList;
  private int lobbyId;


  public SetReadyToPlayEvent(boolean[] readyList, int lobbyid) {
    this.readyList = new boolean[readyList.length];
    System.arraycopy(readyList, 0, this.readyList, 0, readyList.length);
    this.lobbyId = lobbyid;
  }

  public int getLobbyId() {
    return lobbyId;
  }

  public boolean[] getReady() {
    return readyList;
  }
}
