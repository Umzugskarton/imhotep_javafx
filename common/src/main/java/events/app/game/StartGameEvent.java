package events.app.game;

public class StartGameEvent extends GameEvent {
  private int lobbyId;
  public StartGameEvent(int lobbyId){
    this.lobbyId = lobbyId;
  }

  public int getLobbyId() {
    return lobbyId;
  }
}
