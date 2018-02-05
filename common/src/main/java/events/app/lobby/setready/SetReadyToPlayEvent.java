package events.app.lobby.setready;

import events.Event;

public class SetReadyToPlayEvent extends Event {

  private boolean[] readyList;

  public SetReadyToPlayEvent(boolean[] readyList, int lobbyid) {
    this.readyList = new boolean[readyList.length];
    System.arraycopy(readyList, 0, this.readyList, 0, readyList.length);
    this.lobbyId = lobbyid;
  }

  public boolean[] getReady() {
    return readyList;
  }
}
