package events.app.game;

public class UpdatePointsEvent extends GameEvent {

  private int[] points;

  public UpdatePointsEvent(int[] points) {
    this.points = points;
  }

  public int[] getPoints() {
    return points;
  }
}