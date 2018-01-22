package GameEvents;


import SRVevents.Event;

public class ChiselCardEvent implements Event{
  private String type = "ChiselCard";
  private int playerId;

  public ChiselCardEvent(int playerId){
    this.playerId = playerId;
  }

  public int getPlayerId() {
    return playerId;
  }

  public String getType() {
    return type;
  }
}
