package events.app.game;

public class AlreadyAllocatedError extends GameEvent {

  private int shipID;
  private int position;

  public AlreadyAllocatedError() {
  }

  public AlreadyAllocatedError(int shipID, int position) {
    this.shipID = shipID;
    this.position = position;
  }

  public int getPosition() {
    return position;
  }

  public int getShipID() {
    return shipID;
  }
}
