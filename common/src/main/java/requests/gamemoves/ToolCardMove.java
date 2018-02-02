package requests.gamemoves;

import requests.RequestType;

public class ToolCardMove implements Move {

  private RequestType move = RequestType.LEAD_TOOL_CARD;
  private CardType cardType;
  private int lobbyId;

  public ToolCardMove(CardType cardType, int lobbyId) {
    this.cardType = cardType;
    this.lobbyId = lobbyId;
  }

  public int getLobbyId() {
    return lobbyId;
  }

  public CardType getToolType() {
    return this.cardType;
  }

  @Override
  public RequestType getType() {
    return move;
  }
}
