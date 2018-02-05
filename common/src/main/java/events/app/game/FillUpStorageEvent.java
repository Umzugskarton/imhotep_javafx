package events.app.game;

public class FillUpStorageEvent extends GameEvent {

    private int playerId;
    private int storage;

    public FillUpStorageEvent(int playerId, int storage, int lobbyId) {
        super(lobbyId);
        this.playerId = playerId;
        this.storage = storage;
    }

    public int getPlayerId() {
        return playerId;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

}
