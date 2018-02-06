package events.app.game;

public class PositionInvalidError extends GameError {

  public PositionInvalidError(int lobbyId) {
    this.lobbyId = lobbyId;
  }
}