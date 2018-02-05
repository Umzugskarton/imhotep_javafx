package events.app.game;

public class ShipLoadedEvent extends GameEvent {

  private int playerId;
  private int shipID;
  private int[] cargo;
  private int storage;

  public ShipLoadedEvent(int playerId, int shipID, int[] cargo, int storage, int lobbyId) {
    this.playerId = playerId;
    this.shipID = shipID;
    this.cargo = cargo;
    this.storage = storage;
    setLobbyId(lobbyId);
  }

  public int getPlayerId() {
    return playerId;
  }

  public int getShipID() {
    return shipID;
  }

  public int getStorage() {
    return storage;
  }

  public int[] getCargo() {
    return cargo;
  }
}
