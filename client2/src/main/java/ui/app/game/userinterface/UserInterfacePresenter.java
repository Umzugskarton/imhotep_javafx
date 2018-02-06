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

  // Moves
  void sendFillUpStorageMove() {
    eventBus.post(new FillUpStorageMove(lobby.getLobbyId()));
  }

  void sendToolCardMove() {
    String card = this.view.getSelectCardBox().getValue();
    if(card != null) {
      try {
        CardType cardType = CardType.valueOf(card.toUpperCase());
        if(cardType == CardType.CHISEL || cardType == CardType.HAMMER || cardType == CardType.LEVER || cardType == CardType.SAIL) {
          Move move = new ToolCardMove(cardType, lobby.getLobbyId());
          this.connection.send(move);
        }
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

  // Server-Events
  @Subscribe
  private void onToolCardEvent(ToolCardEvent e) {
    // Zeit clientseitig zum Timer hinzufügen
    if(this.turnTimer != null) {
      this.turnTimer.addSeconds(20.0D);
    }
  }

  @Subscribe
  private void onVoyageToStoneSiteExclusiveMove(VoyageToStoneSiteExclusiveEvent e) {
    // Alle Interfaces deaktivieren ausser Schiffe versenden
    toggleInterfaceSectionsEnabled(false, true, false, false);
  }

  @Subscribe
  private void onLoadUpShipExclusiveMove(LoadUpShipExclusiveEvent e) {
    // Alle Interfaces deaktivieren ausser Steine auf Schiffe laden
    toggleInterfaceSectionsEnabled(false, false, true, false);
  }

  @Subscribe
  private void onShipLoadedEvent(ShipLoadedEvent e) {
    updateShipCargoBoxes(e.getCargo(), e.getShipID());
  }

  @Subscribe
  private void onShipDockedEvent(ShipDockedEvent event) {
    removeSiteByTypeFromShipToLocationBox(event.getSite());
    removeShip(event.getShipID());
  }

  @Subscribe
  private void onInventoryUpdateEvent(InventoryUpdateEvent event) {
    myCardTypes = event.getCardTypes().get(this.view.getPlayerId());

    // Karten im Dropdown aktualisieren
    ComboBox<String> selectCardBox = this.view.getSelectCardBox();
    selectCardBox.getItems().clear();

    for(CardType cardType : myCardTypes) {
      if(cardType == CardType.CHISEL || cardType == CardType.HAMMER || cardType == CardType.LEVER || cardType == CardType.SAIL) {
        selectCardBox.getItems().add(cardType.name());
      }
    }
  }

  @Subscribe
  private void update(GameInfoEvent event) {
    storages = event.getStorages();
    round = event.getRound();
    turnTime = event.getTurnTime();
    ships = event.getShips();
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
  public void newTurn(TurnEvent e) {
    if (e.getLobbyId() == lobby.getLobbyId()) {
      this.endTurn(false);

      // Buttons anzeigen, wenn Spieler aktuell an der Reihe ist
      Color userColor = Color.web(lobby.getUserByName(e.getUsername()).getColor(), 0.75F);

      this.changeBgGradient(userColor);
      if (e.isMyTurn()) {
        this.toggleUserInterface(true);
        this.changeBannerLabels("", "", Color.TRANSPARENT);
      } else {
        this.toggleUserInterface(false);
        this.changeBannerLabels(e.getUsername(), "ist gerade am Zug...", userColor);
      }

      this.startTurnTimer();
    }
  }

  // UI
  public void endTurn(boolean noTimeLeft) {
    this.toggleInterfaceSectionsEnabled(false, false, false, false);
    this.stopTurnTimer();
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

  private void toggleUserInterface(boolean show) {
    if(show) {
      toggleInterfaceSectionsEnabled(true, true, true, true);
    }

    this.clearSelections();
    view.getHoldingArea().setVisible(!show);
    view.getHoldingArea().toBack();
    view.getUserInterface().setVisible(show);
    view.getUserInterface().toFront();
  }

  private void toggleInterfaceSectionsEnabled(boolean newStonesEnabled, boolean moveShipEnabled, boolean placeStonesEnabled, boolean playCardEnabled) {
    // Get new stones
    this.view.getGetNewStonesButton().setDisable(!newStonesEnabled);

    // Move ship
    this.view.getSelectShipBox().setDisable(!moveShipEnabled);
    this.view.getSelectShipLocationBox().setDisable(!moveShipEnabled);
    this.view.getMoveShipToLocationButton().setDisable(!moveShipEnabled);

    // Place stones
    this.view.getSelectShipToLocationBox().setDisable(!placeStonesEnabled);
    this.view.getSelectStoneLocationBox().setDisable(!placeStonesEnabled);
    this.view.getPlaceStonesButton().setDisable(!placeStonesEnabled);

    // Play cards
    this.view.getSelectCardBox().setDisable(!playCardEnabled);
    this.view.getPlayCardButton().setDisable(!playCardEnabled);
  }

  private void startTurnTimer() {
    this.stopTurnTimer();
    this.turnTimerThread = new Thread(turnTimer = new TurnTimerThread(this, this.turnTime));
    this.turnTimerThread.start();
  }

  private void stopTurnTimer() {
    Double reset = 0.0;
    eventBus.post(reset);

    if (this.turnTimer != null) {
      this.turnTimer.forceEnd();
    }
    this.turnTimer = null;
    this.turnTimerThread = null;
  }

  private void clearSelections() {
    this.view.getSelectStoneLocationBox().getSelectionModel().clearSelection();
    this.view.getSelectShipToLocationBox().getSelectionModel().clearSelection();
    this.view.getSelectShipLocationBox().getSelectionModel().clearSelection();
    this.view.getSelectShipBox().getSelectionModel().clearSelection();
    this.view.getSelectCardBox().getSelectionModel().clearSelection();
  }

  void updateTurnTimer(double seconds) {
    eventBus.post(seconds / (double) turnTime);
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

  private void updateShipCargoBoxes(int[] cargo, int shipId) {
    for (int i = 0; i < cargo.length; i++) {
      this.ships.get(shipId)[i] = cargo[i];
    }
    this.setStoneLocationComboBox(shipId);
  }

  void setStoneLocationComboBox(int ship) {
    view.getSelectStoneLocationBox().getItems().clear();
    for (int i = 0; i <= ships.get(ship).length - 1; i++) {
      if (ships.get(ship)[i] == -1)
        view.getSelectStoneLocationBox().getItems().add(i);
    }
  }
}
