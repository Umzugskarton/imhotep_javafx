package events.app.game;

public class OutOfStonesError extends GameEvent {

  private int playerId;

  public OutOfStonesError(int playerId, int lobbyId) {
    this.playerId = playerId;
    setLobbyId(lobbyId);
  }
}
