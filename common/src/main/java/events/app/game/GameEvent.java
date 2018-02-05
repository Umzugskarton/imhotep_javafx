package events.app.game;

import events.Event;

public class GameEvent extends Event {

  public GameEvent(int lobbyId) {
    setLobbyId(lobbyId);
  }

  public GameEvent() {
  }
}
