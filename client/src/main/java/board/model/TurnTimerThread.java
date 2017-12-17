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
            while(secondsRemaining > 0) {
                if(secondsRemaining % 5 == 0 || secondsRemaining < 5) {
                    notifyPlayer();
                }
                Thread.sleep(1000);
                this.secondsRemaining -= 1;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void notifyPlayer() {
        Platform.runLater(() -> {
            if(boardPresenter != null) {
                boardPresenter.addLogMessage("Du hast noch " + this.secondsRemaining + " Sekunden für deinen Zug");
            }
        });
    }
}
