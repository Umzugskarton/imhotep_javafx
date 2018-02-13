package requests.gamemoves;

import events.SiteType;

public abstract class VoyageMove implements Move {
    protected int shipId;
    protected SiteType siteType;

  public SiteType getSiteType() {
    return siteType;
  }

  public int getShipId() {
    return shipId;
  }
}
