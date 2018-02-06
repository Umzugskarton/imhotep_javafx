package events.app.game;

public class OutOfStonesError extends GameError {

  private int playerId;

  public OutOfStonesError(int playerId, int lobbyId) {
    this.playerId = playerId;
    this.lobbyId = lobbyId;
  }
}
