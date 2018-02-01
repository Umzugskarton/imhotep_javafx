package events.app.game;

public class ShipAlreadyDockedError extends GameEvent {

  private int shipID;

  public ShipAlreadyDockedError(int shipID) {
    this.shipID = shipID;
  }

  public int getShipID() {
    return shipID;
  }
}
