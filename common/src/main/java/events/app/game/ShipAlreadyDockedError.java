package events.app.game;

public class ShipAlreadyDockedError extends GameEvent {

  private int shipID;

  public ShipAlreadyDockedError(int shipID, int lobbyId) {
    this.shipID = shipID;
    this.lobbyId = lobbyId;
  }

  public int getShipID() {
    return shipID;
  }
}
