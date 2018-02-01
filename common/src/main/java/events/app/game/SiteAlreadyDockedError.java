package events.app.game;

public class SiteAlreadyDockedError extends GameEvent {

  private String site;

  public SiteAlreadyDockedError(String site) {
    this.site = site;
  }

  public String getSite() {
    return site;
  }
}
