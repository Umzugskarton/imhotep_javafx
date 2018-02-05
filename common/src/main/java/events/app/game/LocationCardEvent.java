package events.app.game;

public class LocationCardEvent extends GameEvent {

  private int cardType;

  public LocationCardEvent(int cardType, int lobbyId) {
    this.cardType = cardType;
    this.lobbyId = lobbyId;
  }

  public int getCardType() {
    return this.cardType;
  }
}
