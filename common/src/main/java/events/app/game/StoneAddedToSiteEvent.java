package events.app.game;

import events.SiteType;

import java.util.ArrayList;

public class StoneAddedToSiteEvent extends GameEvent {

  private int playerId;
  private SiteType siteType;
  private ArrayList<Integer> newStones;

  public StoneAddedToSiteEvent(int lobbyId, SiteType siteType, ArrayList<Integer> newStones) {
    this.lobbyId = lobbyId;
    this.newStones = newStones;
    this.siteType = siteType;
  }

  public int getPlayerId() {
    return playerId;
  }

  public SiteType getSiteType() {
    return siteType;
  }

  public ArrayList<Integer> getNewStones() {
    return newStones;
  }
}
