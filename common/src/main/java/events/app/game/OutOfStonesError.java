package events.app.game;

public class OutOfStonesError extends GameEvent {

  private int playerId;

  public OutOfStonesError(int playerId) {
    this.playerId = playerId;
  }
}
