package events.app.game;

import events.SiteType;

public class StoneAddedToSiteEvent extends GameEvent {

  private int playerId;
  private SiteType siteType;

  public StoneAddedToSiteEvent(int lobbyId, int playerId, SiteType siteType) {
    this.lobbyId = lobbyId;
    this.playerId = playerId;
    this.siteType = siteType;
  }

  public int getPlayerId() {
    return playerId;
  }

  public SiteType getSiteType() {
    return siteType;
  }

}
