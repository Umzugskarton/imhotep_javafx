package events.app.game;

public class AlreadyAllocatedError extends GameEvent {

  private int shipID;
  private int position;

  public AlreadyAllocatedError(int shipID, int position, int lobbyId) {
    this.shipID = shipID;
    this.position = position;
    setLobbyId(lobbyId);
  }

  public int getPosition() {
    return position;
  }

  public int getShipID() {
    return shipID;
  }
}
