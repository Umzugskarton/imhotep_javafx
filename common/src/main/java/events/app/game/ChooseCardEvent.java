package events.app.game;



public class ChooseCardEvent extends GameEvent {
    private int playerId;
    private int choosenCardId;


    public ChooseCardEvent(int playerId, int choosenCardId) {
        this.playerId = playerId;
        this.choosenCardId = choosenCardId;
    }


    public int getPlayerId() {
        return playerId;
    }

    public int getChoosenCardId() {
        return choosenCardId;
    }
}
