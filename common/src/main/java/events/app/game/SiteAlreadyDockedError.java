package events.app.game;

import events.SiteType;

public class SiteAlreadyDockedError extends GameEvent {

  public SiteAlreadyDockedError(int lobbyId) {
    this.lobbyId = lobbyId;
  }

  private SiteType site;

  public SiteAlreadyDockedError(SiteType site) {
    this.site = site;
  }

  public SiteType getSite() {
    return site;
  }
}
