package game.board;

public class StatueCard extends Card {

  int[] points = {1, 3, 6, 10, 15};
  int additionalStatuePoints = 2;

  public int calc(int numberOfCards) {
    int additional = numberOfCards-points.length;
    return points[numberOfCards-1-additional] + additional*additionalStatuePoints;
  }
}
