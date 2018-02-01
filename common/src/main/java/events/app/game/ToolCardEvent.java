package events.app.game;

import requests.GameMoves.CardType.Type;

public class ToolCardEvent extends GameEvent {

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
}
