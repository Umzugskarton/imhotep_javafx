package GameMoves;



public class FillUpStorageMove implements Move {
  private String move = "FillUpStorage";

  public FillUpStorageMove() {

  }

  @Override
  public String getType() {
    return move;
  }

}
