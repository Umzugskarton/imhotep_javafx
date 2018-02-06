package ui.app.game.userinterface;


import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import connection.Connection;
import data.lobby.CommonLobby;
import data.user.User;
import events.SiteType;
import events.app.game.*;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import mvp.presenter.Presenter;
import requests.gamemoves.*;
import ui.app.game.board.sites.market.cards.CardView;

import java.util.ArrayList;
import java.util.List;

import static misc.language.TextBundle.getString;

public class UserInterfacePresenter extends Presenter<IUserInterfaceView> {

  private final Connection connection;
  private final User user;
  private CommonLobby lobby;

  private ArrayList<int[]> ships;
  private int round;
  private ArrayList<Integer> storages;
  private ArrayList<CardType> myCardTypes;

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
        stopTurnTimer();
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
    view.getUserInterface().toFront();
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

    for (int i = 0; i < event.getShips().size(); i++) {
      updateShipCargoBoxes(event.getShips().get(i), i);
    }
    setSelectShipLocationBox(event.getSiteTypes());
  }

  @Subscribe
  private void onShipdockedEvent(ShipDockedEvent event) {
    removeSiteByTypeFromShipToLocationBox(event.getSite());
    removeShip(event.getShipID());
  }

  @Subscribe
  private void onInventoryUpdateEvent(InventoryUpdateEvent event) {
    myCardTypes = event.getCardTypes().get(this.view.getPlayerId());

    System.out.println("Inventory geupdated, karten sind da für dich, ole.");
    // TODO Dropdown ändern
  }

  private void removeShip(int ship) {
    for (ComboBox<Integer> box : view.getShipCBoxes()) {
      box.getItems().remove((Integer) ship);
    }
  }

  private void removeSiteByTypeFromShipToLocationBox(SiteType type) {
    view.getSelectShipLocationBox().getItems().remove(getString("site." + type.toString()));
  }

  private void setSelectShipLocationBox(List<SiteType> sites) {
    view.getSelectShipLocationBox().getItems().clear();
    for (SiteType site : sites) {
      view.getSelectShipLocationBox().getItems().add(getString("site." + site.name()));
    }
  }

  @Subscribe
  private void onShipLoadedEvent(ShipLoadedEvent e) {
    updateShipCargoBoxes(e.getCargo(), e.getShipID());
  }

  private void updateShipCargoBoxes(int[] cargo, int shipId) {
    for (int i = 0; i < cargo.length; i++) {
      this.ships.get(shipId)[i] = cargo[i];
    }
    this.setStoneLocationCBox(shipId);
  }


  void setStoneLocationCBox(int ship) {
    view.getSelectStoneLocationBox().getItems().clear();
    for (int i = 0; i <= ships.get(ship).length - 1; i++) {
      if (ships.get(ship)[i] == -1)
        view.getSelectStoneLocationBox().getItems().add(i);
    }
  }

  //Moves
  void sendFillUpStorageMove() {
    eventBus.post(new FillUpStorageMove(lobby.getLobbyId()));
  }

  void sendToolCardMove() {
    String card = this.view.getSelectCardBox().getValue();
    if(card != null) {
      try {
        CardType cardType = CardType.valueOf(card.toUpperCase());
        Move move = new ToolCardMove(cardType, lobby.getLobbyId());
        this.connection.send(move);
      } catch(IllegalArgumentException ex) {
        // TODO Gotta catch'em all
      }
    }
  }

  void sendVoyageToStoneSiteMove(int ship, SiteType to) {
    Move move;
    if (to.equals(SiteType.MARKET)) {
      move = new VoyageToMarketMove(ship, lobby.getLobbyId());
    } else {
      move = new VoyageToStoneSiteMove(ship, to, lobby.getLobbyId());
    }
    stopTurnTimer();
    toggleUserInterface(false);
    this.connection.send(move);
  }

  void sendLoadUpShipMove(int ship, int to) {
    eventBus.post(new LoadUpShipMove(ship, to, lobby.getLobbyId()));
  }
}
