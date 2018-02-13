package requests.gamemoves;

import events.SiteType;
import requests.RequestType;

public class VoyageToStoneSiteMove extends VoyageMove {

  private RequestType move = RequestType.VOYAGE_TO_STONE_SITE;
  private int lobbyId;

  public VoyageToStoneSiteMove(int shipId, SiteType siteType, int lobbyId) {
    this.shipId = shipId;
    this.siteType= siteType;
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
    return move;
  }
}
