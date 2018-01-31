package events.game;


public class StartGameEvent {
  private int lobbyId;
  public StartGameEvent(int lobbyId){
    this.lobbyId = lobbyId;
  }

  public int getLobbyId() {
    return lobbyId;
  }
}
