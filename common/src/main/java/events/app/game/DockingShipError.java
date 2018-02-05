package events.app.game;

public class DockingShipError extends GameEvent {

  public DockingShipError(int lobbyid) {
    this.lobbyId = lobbyid;
  }
}
