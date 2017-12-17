package board.model;

import board.presenter.BoardPresenter;
import javafx.application.Platform;
import main.SceneController;

public class TurnTimerThread implements Runnable {
    private int secondsRemaining;
    private BoardPresenter boardPresenter;

    public TurnTimerThread(BoardPresenter boardPresenter, int seconds) {
        this.secondsRemaining = seconds;
        this.boardPresenter = boardPresenter;
    }

    @Override
    public void run() {
        try {
            while(secondsRemaining >= 0) {
                setRemainingTurnTime();
                Thread.sleep(1000);
                this.secondsRemaining -= 1;
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
}
