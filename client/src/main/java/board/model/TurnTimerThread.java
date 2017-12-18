package board.model;

import board.presenter.BoardPresenter;
import javafx.application.Platform;

public class TurnTimerThread implements Runnable {
    private double secondsRemaining;
    private BoardPresenter boardPresenter;

    public TurnTimerThread(BoardPresenter boardPresenter, int seconds) {
        this.secondsRemaining = (double) seconds;
        this.boardPresenter = boardPresenter;
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
            if(boardPresenter != null) {
                boardPresenter.updateTurnTimer(this.secondsRemaining);
            }
        });
    }

    public void forceEnd() {
        this.secondsRemaining = 0;
    }
}
