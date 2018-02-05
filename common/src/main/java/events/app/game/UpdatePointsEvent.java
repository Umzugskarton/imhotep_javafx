package events.app.game;

public class UpdatePointsEvent extends GameEvent {

  private int[] points;

  public UpdatePointsEvent(int[] points, int lobbyId) {
    this.points = points;
    this.lobbyId = lobbyId;
  }

  public int[] getPoints() {
    return points;
  }
}