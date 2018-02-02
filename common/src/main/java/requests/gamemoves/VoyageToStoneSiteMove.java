package requests.gamemoves;

import requests.RequestType;

public class VoyageToStoneSiteMove implements Move {

  private RequestType move = RequestType.VOYAGE_TO_STONE_SITE;
  private int shipId;
  private String stonesite;
  private int lobbyId;

  public VoyageToStoneSiteMove() {
  }

  public VoyageToStoneSiteMove(int shipId, String stonesite, int lobbyId) {
    this.shipId = shipId;
    this.stonesite = stonesite;
    this.lobbyId = lobbyId;
  }

  public int getLobbyId() {
    return lobbyId;
  }

  public int getShipId() {
    return shipId;
  }

  public String getStonesite() {
    return stonesite;
  }

  public void setShipId(int shipId) {
    this.shipId = shipId;
  }

  public void setStonesite(String stonesite) {
    this.stonesite = stonesite;
  }

  @Override
  public RequestType getType() {
    return move;
  }
}
