package events.app.game;

public class NotEnoughLoadError extends GameEvent {

  private int shipID;

  public NotEnoughLoadError(int shipID, int lobbyId) {
    this.shipID = shipID;
    setLobbyId(lobbyId);
  }

  public int getShipID() {
    return shipID;
  }
}