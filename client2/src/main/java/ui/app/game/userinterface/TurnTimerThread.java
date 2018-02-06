package ui.app.game.userinterface;

import javafx.application.Platform;

public class TurnTimerThread implements Runnable {

  private double secondsRemaining = 20;
  private boolean forcedEnd = false;
  private UserInterfacePresenter userInterfacePresenter;

  public TurnTimerThread(UserInterfacePresenter userInterfacePresenter, int seconds) {
    this.secondsRemaining = (double) seconds;
    this.forcedEnd = false;
    this.userInterfacePresenter = userInterfacePresenter;
  }

  @Override
  public void run() {
    try {
      while (secondsRemaining >= 0.3) {
        setRemainingTurnTime();
        Thread.sleep(100);
        this.secondsRemaining -= 0.1;
      }

      if(!forcedEnd) {
        Platform.runLater(() -> {
          this.userInterfacePresenter.endTurn(false);
        });
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void setRemainingTurnTime() {
    Platform.runLater(() -> {
      if (userInterfacePresenter != null) {
          userInterfacePresenter.updateTurnTimer(this.secondsRemaining);
      }
    });
  }

  public void forceEnd() {
    this.secondsRemaining = -1.0;
    this.forcedEnd = true;
  }

  public void addSeconds(double seconds) {
    this.secondsRemaining += seconds;
  }
}
