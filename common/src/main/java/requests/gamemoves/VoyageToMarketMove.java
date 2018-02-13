package requests.gamemoves;

import events.SiteType;
import requests.RequestType;

public class VoyageToMarketMove extends VoyageMove {

  private RequestType move = RequestType.VOYAGE_TO_MARKET;
  private int lobbyId;

  public VoyageToMarketMove(int shipId, int lobbyId) {
    this.shipId = shipId;
    this.siteType = SiteType.MARKET;
    this.lobbyId = lobbyId;
  }

  public int getLobbyId() {
    return lobbyId;
  }


  @Override
  public RequestType getType() {
    return this.move;
  }

}
