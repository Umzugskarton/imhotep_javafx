package events.app.game;

public class UpdatePointsEvent extends GameEvent {

  private int[] points;

  public UpdatePointsEvent(int[] points, int lobbyId) {
    this.points = points;
    setLobbyId(lobbyId);
  }

  public int[] getPoints() {
    return points;
  }
}