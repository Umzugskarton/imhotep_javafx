package events.app.game;

public class NotEnoughLoadError extends GameError {

  private int shipID;

  public NotEnoughLoadError(int shipID, int lobbyId) {
    this.shipID = shipID;
    this.lobbyId = lobbyId;
  }

  public int getShipID() {
    return shipID;
  }
}