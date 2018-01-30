package GameMoves;

import GameMoves.CardType.Type;

public class ToolCardMove implements Move {

  private String move = "LeadToolCard";
  private Type type;
  private int lobbyId;

  public ToolCardMove(Type type, int lobbyId) {
    this.type = type;
    this.lobbyId = lobbyId;
  }

  public int getLobbyId() {
    return lobbyId;
  }

  public void setType(Type type) {
    this.type = type;
  }

  public Type getToolType() {
    return this.type;
  }

  @Override
  public String getType() {
    return move;
  }
}
