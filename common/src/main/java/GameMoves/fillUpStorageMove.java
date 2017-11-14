package GameMoves;


import java.util.Date;

public class fillUpStorageMove implements Move {
  private String move = "fillUpStorage";

  public fillUpStorageMove() {

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
