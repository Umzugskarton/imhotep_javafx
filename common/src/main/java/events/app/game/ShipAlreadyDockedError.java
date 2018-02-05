package events.app.game;

public class ShipAlreadyDockedError extends GameEvent {

  private int shipID;

  public ShipAlreadyDockedError(int shipID, int lobbyId) {
    this.shipID = shipID;
    setLobbyId(lobbyId);
  }

  public int getShipID() {
    return shipID;
  }
}
