package GameEvents;

import SRVevents.Event;

public class TurnEvent implements Event {

  private String event = "turn";
  private boolean myturn;
  private String username;

  public TurnEvent(boolean myturn, String username) {
    this.myturn = myturn;
    this.username = username;
  }

  public boolean isMyTurn() {
    return myturn;
  }

  public void setMyTurn(boolean myturn) {
    this.myturn = myturn;
  }

  public String getUsername() {
    return this.username;
  }

}
