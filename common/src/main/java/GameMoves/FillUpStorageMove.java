package GameMoves;


import java.util.Date;

public class FillUpStorageMove implements Move {
  private String move = "FillUpStorage";

  public FillUpStorageMove() {

  }

  @Override
  public String getType() {
    return move;
  }

  @Override
  public Date getDate() {
    return null;
  }
}
