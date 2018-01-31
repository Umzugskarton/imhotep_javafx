package GameEvents;

import SRVevents.Event;

public class ShipLoadedEvent implements Event {

  private String event = "ShipLoaded";

  private int playerId;
  private int shipID;
  private int[] cargo;
  private int storage;

  public ShipLoadedEvent(int playerId, int shipID, int[] cargo, int storage) {
    this.playerId = playerId;
    this.shipID = shipID;
    this.cargo = cargo;
    this.storage = storage;
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

  public String getEvent() {
    return event;
  }
}
