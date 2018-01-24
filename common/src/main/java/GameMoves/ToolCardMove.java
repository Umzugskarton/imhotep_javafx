package GameMoves;

import java.util.Date;

public class ToolCardMove implements Move{
  private String move = "LeadToolCard";
  private String name;
  private int lobbyId;

  public ToolCardMove() {
  }

  public ToolCardMove(String name, int lobbyId) {
    this.name = name;
    this.lobbyId = lobbyId;
  }

  public int getLobbyId() {
    return lobbyId;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName(){
    return this.name;
  }

  @Override
  public String getType() {
    return move;
  }


}
