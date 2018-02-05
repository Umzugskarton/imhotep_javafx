package events.app.game;

public class PositionInvalidError extends GameEvent {

  public PositionInvalidError(int lobbyId) {
    setLobbyId(lobbyId);
  }
}