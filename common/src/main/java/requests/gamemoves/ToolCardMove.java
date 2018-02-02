package requests.gamemoves;

public class ToolCardMove implements Move {

  private String move = "LeadToolCard";
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
  public MoveType getMoveType() {
    return MoveType.LEAD_TOOL_CARD;
  }

  @Override
  public String getType() {
    return move;
  }
}
