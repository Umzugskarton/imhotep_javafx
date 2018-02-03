package ui.app.game.userinterface;


import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import connection.Connection;
import data.lobby.CommonLobby;
import data.user.User;
import events.app.game.GameInfoEvent;
import events.app.game.ShipLoadedEvent;
import events.app.game.TurnEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import mvp.presenter.Presenter;
import requests.gamemoves.FillUpStorageMove;
import requests.gamemoves.LoadUpShipMove;
import requests.gamemoves.VoyageToStoneSiteMove;

import java.util.ArrayList;

public class UserInterfacePresenter extends Presenter<IUserInterfaceView> {

  private final Connection connection;
  private final User user;
  private CommonLobby lobby;

  private ArrayList<int[]> ships;
  private int round;
  private ArrayList<Integer> storages;

  private int turnTime;
  private Thread turnTimerThread;
  private TurnTimerThread turnTimer;


  UserInterfacePresenter(IUserInterfaceView view, EventBus eventBus, Connection connection,
                         User user, CommonLobby lobby) {
    super(view, eventBus);
    this.connection = connection;
    this.user = user;
    this.lobby = lobby;
    bind();
  }

  public void bind() {
    eventBus.register(this);
  }

  public Connection getClientSocket() {
    return this.connection;
  }

  // Turns
  private void endTurn(boolean noTimeLeft) {
    this.toggleUserInterface(false);
    if (noTimeLeft) {
      this.stopTurnTimer();
      this.changeBannerLabels("Zug beendet!", "Nächster Zug wird vorbereitet...",
              Color.web("#cdb39c"));
      this.changeBgGradient(Color.web("#cdb39c"));
    }
  }

  private void changeBgGradient(Color color) {
    Stop[] stops = new Stop[]{
            new Stop(0, color),
            new Stop(1, Color.TRANSPARENT)};
    LinearGradient linearGradient =
            new LinearGradient(0, 0, 0, 0.1, true, CycleMethod.NO_CYCLE, stops);
    this.view.getPlayerColorRectangle().setFill(linearGradient);
  }


  private void changeBannerLabels(String text, String subText, Color textColor) {
    this.view.getUiBannerLabel().setText(text);
    this.view.getUiBannerSmallLabel().setText(subText.toUpperCase());
    this.view.getUiBannerLabel().setTextFill(textColor);
  }

  @Subscribe
  public void newTurn(TurnEvent e) {
    if (e.getLobbyId() == lobby.getLobbyId()) {
      // Buttons anzeigen, wenn Spieler aktuell an der Reihe ist
      this.toggleUserInterface(e.isMyTurn());
      Color userColor = Color.web(lobby.getUserByName(e.getUsername()).getColor(), 0.75F);
      this.changeBgGradient(userColor);
      if (e.isMyTurn()) {
        this.changeBannerLabels("", "", Color.TRANSPARENT);
      } else {
        this.changeBannerLabels(e.getUsername(), "ist gerade am Zug...", userColor);
      }
      this.startTurnTimer();
    }
  }

  private void toggleUserInterface(boolean show) {
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

  private void stopTurnTimer() {
    Double reset = 0.0;
    eventBus.post(reset);
    if (this.turnTimerThread != null) {
      this.turnTimer.forceEnd();
      this.turnTimer = null;
      this.turnTimerThread = null;
    }
  }

  void updateTurnTimer(double seconds) {
    eventBus.post(seconds / (double) turnTime);

    if (seconds <= 0.0) {
      this.endTurn(true);
    }
  }

  @Subscribe
  private void update(GameInfoEvent event) {
    storages = event.getStorages();
    round = event.getRound();
    turnTime = event.getTurnTime();
    if (ships == null) {
      ships = event.getShips();
    }
    for (ComboBox<Integer> shipBox : view.getShipCBoxes()) {
      shipBox.getItems().clear();
      for (int i = 0; i <= ships.size() - 1; i++) {
        shipBox.getItems().add(i);
      }
    }
    setSelectShipLocationBox(event.getSitesAllocation(), event.getSiteString());
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

  void setStoneLocationCBox(int ship) {
    view.getSelectStoneLocationBox().getItems().clear();
    for (int i = 0; i <= ships.get(ship).length - 1; i++) {
      if (ships.get(ship)[i] == -1) {
        view.getSelectStoneLocationBox().getItems().add(i);
      }
    }
  }

  //Moves
  void sendFillUpStorageMove() {
    eventBus.post(new FillUpStorageMove(lobby.getLobbyId()));
  }

  void sendVoyageToStoneSiteMove(int ship, String to) {
    this.connection.send(new VoyageToStoneSiteMove(ship, to, lobby.getLobbyId()));
  }

  void sendLoadUpShipMove(int ship, int to) {
    eventBus.post(new LoadUpShipMove(ship, to, lobby.getLobbyId()));
  }
}
