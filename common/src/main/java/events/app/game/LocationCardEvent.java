package events.app.game;

public class LocationCardEvent {

    private int cardType;

    public LocationCardEvent(int cardType) {
        this.cardType = cardType;
    }

    public int getCardType() {
        return this.cardType;
    }
}
