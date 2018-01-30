package ui.app.game.userinterface;


import GameEvents.TurnEvent;
import com.google.common.eventbus.EventBus;
import connection.Connection;
import data.lobby.Lobby;
import data.user.User;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import mvp.presenter.Presenter;

public class UserInterfacePresenter extends Presenter<IUserInterfaceView> {

  private final Connection connection;
  private final User user;
  private Lobby lobby;

  private int turnTime;
  private Thread turnTimerThread;
  private TurnTimerThread turnTimer;


  public UserInterfacePresenter(IUserInterfaceView view, EventBus eventBus, Connection connection, User user, Lobby lobby) {
    super(view, eventBus);
    this.connection = connection;
    this.user = user;
    this.lobby = lobby;
  }

  public Connection getClientSocket() {
    return this.connection;
  }

  // Turns
  public void endTurn(boolean noTimeLeft) {
    this.toggleUserInterface(false);

    if (noTimeLeft) {
      this.stopTurnTimer();

      this.changeBannerLabels("Zug beendet!", "Nächster Zug wird vorbereitet...", Color.web("#cdb39c"));
      this.changeBgGradient(Color.web("#cdb39c"));
    }
  }

  public void changeBgGradient(Color color) {
    Stop[] stops = new Stop[]{
            new Stop(0, color),
            new Stop(1, Color.TRANSPARENT)};

    LinearGradient linearGradient =
            new LinearGradient(0, 0, 0, 0.1, true, CycleMethod.NO_CYCLE, stops);

    this.view.getPlayerColorRectangle().setFill(linearGradient);
  }


  public void changeBannerLabels(String text, String subText, Color textColor) {
    this.view.getUiBannerLabel().setText(text);
    this.view.getUiBannerSmallLabel().setText(subText.toUpperCase());
    this.view.getUiBannerLabel().setTextFill(textColor);
  }

  public void newTurn(TurnEvent e) {
    // Buttons anzeigen, wenn Spieler aktuell an der Reihe ist
    this.toggleUserInterface(e.isMyTurn());
    Color userColor = Color.web(lobby.getUserbyName(e.getUsername()).getColor(), 0.75F);

    this.view.getCurrentPlayerLabel().setText(e.getUsername());
    this.changeBgGradient(userColor);

    // Aktuellen Spielernamen fettgedruckt anzeigen wenn der Client der aktuelle Spieler ist
    if (e.isMyTurn()) {
      this.view.getCurrentPlayerLabel().setFont(Font.font("Calibri", FontWeight.BOLD, 14));
      this.changeBannerLabels("", "", Color.TRANSPARENT);
    } else {
      this.view.getCurrentPlayerLabel().setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
      this.changeBannerLabels(e.getUsername(), "ist gerade am Zug...", userColor);
    }

    this.startTurnTimer();
  }

  public void toggleUserInterface(boolean show) {
    view.getHoldingArea().setVisible(!show);
    view.getHoldingArea().toBack();
    view.getUserInterface().setVisible(show);
  }

  // Timer
  private void startTurnTimer() {
    this.stopTurnTimer();
    this.turnTimerThread = new Thread(turnTimer = new TurnTimerThread(this, this.turnTime));
    this.turnTimerThread.start();
  }

  public void stopTurnTimer() {
    this.view.getTurnTimerProgress().setProgress(0.0);
    if (this.turnTimerThread != null) {
      this.turnTimer.forceEnd();
      this.turnTimer = null;
      this.turnTimerThread = null;
    }
  }

  public void updateTurnTimer(double seconds) {
    this.view.getTurnTimerProgress().setProgress(seconds / (double) turnTime);

    if (seconds <= 0.0) {
      this.endTurn(true);
    }
  }

}
