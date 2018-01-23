package GameEvents;

import SRVevents.Event;

public class ToolCardEvent implements Event{
  private String type = "ToolCard";
  private String toolCard;
  private boolean active;
  private int playerId;

  public ToolCardEvent(String toolCard, int playerId, boolean active){
    this.toolCard  = toolCard;
    this.playerId = playerId;
    this.active=active;
  }

  public int getPlayerId() {
    return playerId;
  }

  public boolean isActive() {
    return active;
  }

  public String getToolCard() {
    return toolCard;
  }

  public String getType() {
    return type;
  }
}
