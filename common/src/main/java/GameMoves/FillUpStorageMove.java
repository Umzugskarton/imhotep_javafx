package GameMoves;

public class FillUpStorageMove implements Move {

  private String move = "FillUpStorage";
  private int lobbyId;

  public FillUpStorageMove() {
  }

  public FillUpStorageMove(int lobbyId) {
    this.lobbyId = lobbyId;
  }

  public int getLobbyId() {
    return lobbyId;
  }

  @Override
  public String getType() {
    return move;
  }
}
