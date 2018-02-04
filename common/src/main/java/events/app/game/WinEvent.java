package events.app.game;

public class WinEvent extends GameEvent {

  private String winner;
  private boolean winning;
  private String[][] playersResult;

  public WinEvent(String winner, String[][] playersResult) {
    this.winner = winner;
    this.playersResult = playersResult;
  }

  public String getWinner() {
    return winner;
  }

  public boolean isWinning() {
    return winning;
  }

  public void setWinning(boolean winning) {
    this.winning = winning;
  }

  public String[][] getResults() {
    return playersResult;
  }


}
