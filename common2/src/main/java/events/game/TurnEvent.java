package events.game;

public class TurnEvent extends GameEvent {
  boolean myturn;

  public TurnEvent(boolean myturn) {
    this.myturn = myturn;
  }

  public boolean isMyturn() {
    return myturn;
  }

  public void setMyturn(boolean myturn) {
    this.myturn = myturn;
  }
}
