package events.game;

public class FillUpStorageEvent extends GameEvent {
  private String event = "filUpStorage";
  private int playerId;
  private boolean[] storage;

  public FillUpStorageEvent() {
  }

  public FillUpStorageEvent(int playerId, boolean[] storage) {
    this.playerId = playerId;
    this.storage = storage;
  }

  public boolean[] getStorage() {
    return storage;
  }

  public String getEvent() {
    return event;
  }

  public void setStorage(boolean[] storage) {
    this.storage = storage;
  }
}
