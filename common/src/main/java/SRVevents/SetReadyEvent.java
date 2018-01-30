package SRVevents;

public class SetReadyEvent implements Event {

  private String event = "setReady";
  private boolean[] readyList;
  private int lobbyId;


  public SetReadyEvent(boolean[] readyList, int lobbyid) {
    this.readyList = new boolean[readyList.length];
    System.arraycopy(readyList, 0, this.readyList, 0, readyList.length);
    this.lobbyId = lobbyid;
  }

  public int getLobbyId() {
    return lobbyId;
  }

  public String getEvent() {
    return this.event;
  }

  public String getMsg() {
    return this.msg;
  }

  public boolean[] getReady() {
    return readyList;
  }
}
