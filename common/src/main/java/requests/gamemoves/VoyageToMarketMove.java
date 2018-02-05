package requests.gamemoves;

import requests.RequestType;

public class VoyageToMarketMove implements Move {

  private RequestType move = RequestType.VOYAGE_TO_MARKET;
  private int shipId;
  private int lobbyId;

  public VoyageToMarketMove(int shipId, int lobbyId) {
    this.shipId = shipId;
    this.lobbyId = lobbyId;
  }

  public int getLobbyId() {
    return lobbyId;
  }

  public int getShipId() {
    return shipId;
  }

  @Override
  public RequestType getType() {
    return this.move;
  }

}
