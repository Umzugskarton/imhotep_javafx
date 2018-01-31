package GameEvents;

import SRVevents.Event;

public class WinEvent implements Event {

  private String winner;
  private boolean winning;

  public WinEvent(String winner) {
    this.winner = winner;
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
}
