package GameEvents;

import GameMoves.CardType.Type;
import SRVevents.Event;

public class ToolCardEvent implements Event {

  private String type = "ToolCard";
  private Type toolCard;
  private boolean active;
  private int playerId;

  public ToolCardEvent(Type toolCard, int playerId, boolean active) {
    this.toolCard = toolCard;
    this.playerId = playerId;
    this.active = active;
  }

  public int getPlayerId() {
    return playerId;
  }

  public boolean isActive() {
    return active;
  }

  public Type getToolCard() {
    return toolCard;
  }

  public String getType() {
    return type;
  }
}
