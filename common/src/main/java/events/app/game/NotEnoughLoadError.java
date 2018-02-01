package events.app.game;


public class NotEnoughLoadError extends GameEvent {

  private int shipID;

  public NotEnoughLoadError(int shipID) {
    this.shipID = shipID;
  }

  public int getShipID() {
    return shipID;
  }
}