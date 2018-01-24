package board.presenter;

import GameEvents.FillUpStorageEvent;
import GameEvents.GameInfoEvent;
import GameEvents.ShipDockedEvent;
import GameEvents.ShipLoadedEvent;
import GameEvents.TurnEvent;
import GameMoves.FillUpStorageMove;
import GameMoves.LoadUpShipMove;
import GameMoves.VoyageToStoneSiteMove;
import board.model.TurnTimerThread;
import board.view.BoardViewImplFx;
import board.view.BurialChamberViewImplFx;
import board.view.ObelisksViewImplFx;
import board.view.PyramidViemImplFx;
import board.view.ShipViewImplFx;
import board.view.StorageViewImplFx;
import board.view.TempleViewImplFx;
import commonLobby.CLTLobby;
import commonLobby.LobbyUser;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import main.SceneController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BoardPresenter {

  private final Logger log = LoggerFactory.getLogger(getClass().getName());

  private BoardViewImplFx view;
  private SceneController sc;
  private CLTLobby lobby;
  private ArrayList<StoragePresenter> storagePresenters = new ArrayList<>();
  private ArrayList<ShipPresenter> shipPresenters = new ArrayList<>();

  //StoneSitePresenter
  private PyramidPresenter pyramidsPresenter;
  private TemplePresenter templePresenter;
  private BurialChamberPresenter burialPresenter;
  private ObelisksPresenter obelisksPresenter;

  private Map<String, StoneSitePresenter> sitePresenters = new HashMap<>();

  //Board Variables
  private int myID = -1;
  private ArrayList<int[]> ships;
  private int round;
  private ArrayList<Integer> storages;
  private String[] order;
  private int turnTime;
  private Thread turnTimerThread;
  private TurnTimerThread turnTimer;

  // Konstruktor
  public BoardPresenter(BoardViewImplFx view, SceneController sc, CLTLobby legacy) {
    this.lobby = legacy;
    this.view = view;
    this.sc = sc;
    this.turnTime = 0;
    int render = 0;
    this.turnTimerThread = null;
    this.turnTimer = null;

    try {
      AnchorPane pyramid;
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("/fxml/PyramidsView.fxml"));
      pyramid = loader.load();
      PyramidViemImplFx pyramidController = loader.getController();
      pyramidsPresenter = new PyramidPresenter(lobby, pyramidController);

      view.getStoneSiteGrid().add(pyramid, 0, 1);

      AnchorPane temple;
      FXMLLoader loader1 = new FXMLLoader();
      loader1.setLocation(getClass().getResource("/fxml/TempleView.fxml"));
      temple = loader1.load();
      TempleViewImplFx templeController = loader1.getController();
      templePresenter = new TemplePresenter(lobby, templeController);

      view.getStoneSiteGrid().add(temple, 0, 2);

      AnchorPane burial;
      FXMLLoader loader2 = new FXMLLoader();
      loader2.setLocation(getClass().getResource("/fxml/BurialChamberView.fxml"));
      burial = loader2.load();
      BurialChamberViewImplFx burialFx = loader2.getController();
      burialPresenter = new BurialChamberPresenter(lobby, burialFx);
      view.getStoneSiteGrid().add(burial, 0, 3);

      AnchorPane obelisks;
      FXMLLoader loader3 = new FXMLLoader();
      loader3.setLocation(getClass().getResource("/fxml/ObelisksView.fxml"));
      obelisks = loader3.load();
      ObelisksViewImplFx obelisksFx = loader3.getController();
      obelisksPresenter = new ObelisksPresenter(lobby, obelisksFx);
      view.getStoneSiteGrid().add(obelisks, 0, 4);

    } catch (IOException e) {
      e.printStackTrace();
    }

    for (LobbyUser user : lobby.getUsers()) {
      try {
        AnchorPane root;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/StorageView.fxml"));
        root = loader.load();
        StorageViewImplFx storage = loader.getController();
        StoragePresenter storagePresenter = new StoragePresenter(user, storage);
        storagePresenters.add(storagePresenter);
        view.addHouse(render, root);
        render++;
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    sitePresenters.put("Pyramids", pyramidsPresenter);
    sitePresenters.put("Temple", templePresenter);
    sitePresenters.put("BurialChamber", burialPresenter);
    sitePresenters.put("Obelisks", obelisksPresenter);
  }

  // Moves
  public void sendFillUpStorageMove() {
    FillUpStorageMove fillUpStorageMove = new FillUpStorageMove();
    this.sc.getClientSocket().send(fillUpStorageMove);
  }

  public void receiveFillUpStorageEvent(FillUpStorageEvent e) {
    this.updateStorages(e);
    this.endTurn(false);
  }

  public void sendLoadUpShipMove(int ship, int position) {
    if (storagePresenters.get(myID).getStoneCount() > 0) {
      LoadUpShipMove loadUpShipMove = new LoadUpShipMove(ship, position);
      sc.getClientSocket().send(loadUpShipMove);
    }
  }

  public void receiveShipLoadedEvent(ShipLoadedEvent e) {
    this.updateShipCargoById(e);
    this.endTurn(false);
  }

  public void sendVoyageToStoneSiteMove(int shipID, String site) {
    if (!shipPresenters.get(shipID).isDocked()) {
      VoyageToStoneSiteMove move = new VoyageToStoneSiteMove(shipID, site);
      sc.getClientSocket().send(move);
    }
  }

  // Board View
  public BoardViewImplFx getView() {
    return view;
  }

  public void updateBoard(GameInfoEvent event) {
    if (myID == -1) {
      myID = event.getMyId();
    }
    storages = event.getStorages();
    round = event.getRound();
    order = event.getOrder();
    turnTime = event.getTurnTime();

    for (Pane pierPane : this.view.getPiers().values()) {
      pierPane.getChildren().clear();
      ships = null;
    }

    if (ships == null) {
      ships = event.getShips();
      setShips();
    }

    this.view.getRoundLabel().setText(round + " / 6");

    int i = 0;
    view.getSelectShipLocationBox().getItems().clear();
    for (String site : event.getSiteString()) {
      if (event.getSitesAllocation()[i] == -1) {
        view.getSelectShipLocationBox().getItems().add(site);
      }
      i++;
    }
    updateView();
  }

  private void updateView() {
    setStorages();
  }

  private void setStorages() {
    int i = 0;
    for (int stone : storages) {
      storagePresenters.get(i).setStoneCount(stone);
      i++;
    }
  }

  public void updateStorages(FillUpStorageEvent event) {
    storagePresenters.get(event.getPlayerId()).setStoneCount(event.getStorage());
  }

  private void setShips() {
    this.shipPresenters = new ArrayList<>();
    int render = 0;
    for (int[] ship : ships) {
      try {
        AnchorPane root;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/ShipView.fxml"));
        root = loader.load();
        ShipViewImplFx shipView = loader.getController();
        ShipPresenter shipPresenter = new ShipPresenter(lobby, shipView, ship);
        shipPresenters.add(shipPresenter);
        view.addShip(render, root);
        render++;
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    for (ComboBox<Integer> shipBox : view.getShipCBoxes()) {
      shipBox.getItems().clear();
      for (int i = 0; i <= ships.size() - 1; i++) {
        shipBox.getItems().add(i);
      }
    }
  }

    public void shipDocked(ShipDockedEvent event){
      shipPresenters.get(event.getShipID()).setLocation(event.getSite());
      view.getPierByName(event.getSite()).getChildren().add(view.removeShipPaneById(event.getShipID()));
      StoneSitePresenter presenter = sitePresenters.get(event.getSite());
      presenter.setStones(event.getNewstones());
    }

    public void updatePoints(UpdatePointsEvent event){
      updatePointsView(event.getPoints());
    }

  public void updateShipCargoById(ShipLoadedEvent e) {
    storagePresenters.get(e.getPlayerId()).setStoneCount(e.getStorage());
    shipPresenters.get(e.getShipID()).setCargo(e.getCargo());

    for (int i = 0; i < e.getCargo().length; i++) {
      this.ships.get(e.getShipID())[i] = e.getCargo()[i];
    }

    this.setStoneLocationCBox(e.getShipID());
  }


  // Turns
  public void endTurn(boolean noTimeLeft) {
    this.toggleUserInterface(false);

    if (noTimeLeft) {
      this.stopTurnTimer();

      this.changeBannerLabels("Zug beendet!", "Nächster Zug wird vorbereitet...",
          Color.web("#cdb39c"));
      this.changeBgGradient(Color.web("#cdb39c"));
    }
  }

  public void newTurn(TurnEvent e) {
    // Buttons anzeigen, wenn Spieler aktuell an der Reihe ist
    this.toggleUserInterface(e.isMyTurn());
    Color userColor = Color.web(lobby.getUserByName(e.getUsername()).getColor(), 0.75F);

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

  // User Interface
  public void setStoneLocationCBox(int ship) {
    view.getSelectStoneLocationBox().getItems().clear();
    for (int i = 0; i <= ships.get(ship).length - 1; i++) {
      if (ships.get(ship)[i] == -1) {
        view.getSelectStoneLocationBox().getItems().add(i);
      }
    }
  }

  public void toggleUserInterface(boolean show) {
    view.getHoldingArea().setVisible(!show);
    view.getHoldingArea().toBack();
    view.getUserInterface().setVisible(show);
  }

  public void fullscreen() {
    sc.toggleFullscreen();
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

  public void updatePointsView(int[] pointArray) {
      int highestPoints = 0;
      int playerWithHighestPoints = 0;

      // Punktestand aktualisieren
      for (int i = 0; i < pointArray.length; i++) {
          int points = pointArray[i];
          if (points > highestPoints) {
              highestPoints = points;
              playerWithHighestPoints = i;
      }

      storagePresenters.get(playerWithHighestPoints).highlightPointsLabel(false);
      storagePresenters.get(i).setPoints(points);
    }

    if (highestPoints != 0) {
      storagePresenters.get(playerWithHighestPoints).highlightPointsLabel(true);
    }
  }
}
