package ui.app.game.userinterface;


import GameEvents.FillUpStorageEvent;
import GameEvents.GameInfoEvent;
import GameEvents.ShipLoadedEvent;
import GameEvents.TurnEvent;
import GameMoves.FillUpStorageMove;
import GameMoves.LoadUpShipMove;
import GameMoves.VoyageToStoneSiteMove;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import connection.Connection;
import data.lobby.Lobby;
import data.user.User;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import mvp.presenter.Presenter;

import java.util.ArrayList;

public class UserInterfacePresenter extends Presenter<IUserInterfaceView> {

  private final Connection connection;
  private final User user;
  private Lobby lobby;

  private ArrayList<int[]> ships;
  private int round;
  private ArrayList<Integer> storages;

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
    Double reset = 0.0;
    eventBus.post(reset);
    if (this.turnTimerThread != null) {
      this.turnTimer.forceEnd();
      this.turnTimer = null;
      this.turnTimerThread = null;
    }
  }

  public void updateTurnTimer(double seconds) {
    eventBus.post(seconds / (double) turnTime);

    if (seconds <= 0.0) {
      this.endTurn(true);
    }
  }

  @Subscribe
  private void update(GameInfoEvent event) {
    Platform.runLater(() -> {
              storages = event.getStorages();
              round = event.getRound();
              turnTime = event.getTurnTime();
              if (ships == null) {
                ships = event.getShips();
              }
              setSelectShipLocationBox(event.getSitesAllocation(), event.getSiteString());
            }
    );
  }

  private void setSelectShipLocationBox(int[] sitesAllocation, String[] sites) {
    int i = 0;
    view.getSelectShipLocationBox().getItems().clear();
    for (String site : sites) {
      if (sitesAllocation[i] == -1) {
        view.getSelectShipLocationBox().getItems().add(site);
      }
      i++;
    }
  }

  @Subscribe
  public void updateShipCargoBoxes(ShipLoadedEvent e) {
    for (int i = 0; i < e.getCargo().length; i++) {
      this.ships.get(e.getShipID())[i] = e.getCargo()[i];
    }
    this.setStoneLocationCBox(e.getShipID());
  }


  public void setStoneLocationCBox(int ship) {
    view.getSelectStoneLocationBox().getItems().clear();
    for (int i = 0; i <= ships.get(ship).length - 1; i++) {
      if (ships.get(ship)[i] == -1)
        view.getSelectStoneLocationBox().getItems().add(i);
    }
  }


  //Moves
  public void sendFillUpStorageMove() {
    eventBus.post(new FillUpStorageMove());
  }

  public void sendVoyageToStoneSiteMove(int ship, String to) {
    eventBus.post(new VoyageToStoneSiteMove(ship, to));
  }

  public void sendLoadUpShipMove(int ship, int to) {
    eventBus.post(new LoadUpShipMove(ship, to));
  }
}
