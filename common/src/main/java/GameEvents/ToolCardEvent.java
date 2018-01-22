package GameEvents;

import SRVevents.Event;

public class ToolCardEvent implements Event{
  private String type = "ToolCard";
  private String toolCard;
  private int playerId;

  public ToolCardEvent(String toolCard, int playerId){
    this.toolCard  = toolCard;
    this.playerId = playerId;
  }

  public int getPlayerId() {
    return playerId;
  }

  public String getType() {
    return type;
  }
}
