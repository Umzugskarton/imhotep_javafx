package GameMoves;


public class ToolCardMove implements Move{
  private String move = "LeadToolCard";
  private String name;

  public ToolCardMove() {

  }

  public ToolCardMove(String name) {
    this.name = name;

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
