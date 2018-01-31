package ui.app.game.userinterface;

import javafx.application.Platform;

public class TurnTimerThread implements Runnable {
  private double secondsRemaining;
  private UserInterfacePresenter userInterfacePresenter;

  public TurnTimerThread(UserInterfacePresenter userInterfacePresenter, int seconds) {
    this.secondsRemaining = (double) seconds;
    this.userInterfacePresenter = userInterfacePresenter;
  }

  @Override
  public void run() {
    try {
      while(secondsRemaining >= -0.2) {
        setRemainingTurnTime();
        Thread.sleep(100);
        this.secondsRemaining -= 0.1;
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void setRemainingTurnTime() {
    Platform.runLater(() -> {
      if(userInterfacePresenter != null) {
        userInterfacePresenter.updateTurnTimer(this.secondsRemaining);
      }
    });
  }

  public void forceEnd() {
    this.secondsRemaining = -1.0;
  }
}
