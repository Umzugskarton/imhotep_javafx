package events.app.game;

import requests.gamemoves.CardType;

public class ToolCardEvent extends GameEvent {

  private CardType toolCard;
  private boolean active;
  private int playerId;

  public ToolCardEvent(CardType toolCard, int playerId, boolean active) {
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

  public CardType getToolCard() {
    return toolCard;
  }
}
