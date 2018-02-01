package events.app.game;


public class ChooseCardEvent extends GameEvent {
    private int playerId;

    public ChooseCardEvent(int playerId) {
        this.playerId = playerId;
    }
}
