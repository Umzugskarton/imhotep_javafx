package events.app.game;

public class TurnEvent extends GameEvent {

  private boolean myturn;
  private String username;

  public TurnEvent(boolean myturn, String username, int lobbyId) {
    this.myturn = myturn;
    this.username = username;
    this.lobbyId = lobbyId;
  }

  public boolean isMyTurn() {
    return myturn;
  }

  public void setMyTurn(boolean myturn) {
    this.myturn = myturn;
  }

  public String getUsername() {
    return this.username;
  }

}
