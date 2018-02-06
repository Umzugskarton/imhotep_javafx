package events.app.game;

public class VoyageToStoneSiteManualDumpEvent extends GameEvent {
  private int shipid;

  public VoyageToStoneSiteManualDumpEvent(int lobbyId, int shipId) {
    this.lobbyId = lobbyId;
    this.shipid = shipId;
  }

  public int getShipid() {
    return shipid;
  }
}
