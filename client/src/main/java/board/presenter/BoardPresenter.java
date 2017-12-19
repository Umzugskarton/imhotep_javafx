package board.presenter;

import GameEvents.FillUpStorageEvent;
import GameEvents.GameInfoEvent;
import GameEvents.ShipLoadedEvent;
import GameEvents.TurnEvent;
import GameMoves.FillUpStorageMove;
import GameMoves.LoadUpShipMove;
import board.model.TurnTimerThread;
import board.view.BoardViewImplFx;
import board.view.ShipViewImplFx;
import board.view.StorageViewImplFx;
import commonLobby.CLTLobby;
import commonLobby.LobbyUser;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
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
import java.util.Optional;


public class BoardPresenter {
    private final Logger log = LoggerFactory.getLogger(getClass().getName());

    private BoardViewImplFx view;
    private SceneController sc;
    private CLTLobby lobby;
    private ArrayList<StoragePresenter> storagePresenters = new ArrayList<>();
    private ArrayList<ShipPresenter> shipPresenters = new ArrayList<>();

    //Board Variables
    private int myid = -1;
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

    // Board View
    public BoardViewImplFx getView() {
        return view;
    }

    public void updateBoard(GameInfoEvent event) {
        if (myid == -1){
           myid= event.getMyId();
        }
        storages = event.getStorages();
        if (ships == null) {
          ships = event.getShips();
          setShips();
        }
        round = event.getRound();
        order = event.getOrder();
        turnTime = event.getTurnTime();

        this.view.getRoundLabel().setText(round + " / 6");

        updateView();
    }

    private void updateView() {
        setStorages();
    }

    private void setStorages() {
        int i = 0 ;
        for (int stone : storages) {
            storagePresenters.get(i).setStoneCount(stone);
            i++;
        }
    }

    public void updateStorages(FillUpStorageEvent event) {
        storagePresenters.get(event.getPlayerId()).setStoneCount(event.getStorage());
    }

    private void  setShips(){
      int render = 0;
      for (int[] ship : ships){
        try {
          AnchorPane root;
          FXMLLoader loader = new FXMLLoader();
          loader.setLocation(getClass().getResource("/fxml/ShipView.fxml"));
          root = loader.load();
          ShipViewImplFx shipView = loader.getController();
          ShipPresenter shipPresenter = new ShipPresenter( lobby, shipView, ship);
          shipPresenters.add(shipPresenter);
          view.addShip(render, root);
          render++;
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
        for (ComboBox<Integer> x : view.getShipCBoxes()){
          for (int i = 0 ; i<= ships.size() -1 ; i++){
              x.getItems().add( i );
          }
        }
    }

    public void updateShipCargobyId(ShipLoadedEvent e){
        storagePresenters.get(e.getPlayerId()).setStoneCount(e.getStorage());
        shipPresenters.get(e.getShipID()).setCargo(e.getCargo());
    }

    public void setStoneLocationCBox(int ship){
        view.getSelectStoneLocationBox().getItems().clear();
        for (int i=0; i <= ships.get(ship).length-1; i++){
            if (ships.get(ship)[i] == -1)
                view.getSelectStoneLocationBox().getItems().add(i);
        }
    }

    public void sendLoadUpShipMove(int ship, int position){
        if(storagePresenters.get(myid).getStoneCount() > 0) {
            LoadUpShipMove loadUpShipMove = new LoadUpShipMove(ship, position);
            sc.getClientSocket().send(loadUpShipMove);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Steine aufladen nicht möglich");
            alert.setHeaderText("Keine Steine im Lager");
            alert.setContentText("Möchten Sie neue Steine anfordern?");
            alert.showAndWait();
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent()) {
                if (result.get() == ButtonType.OK) {
                    sendFillUpStorageMove();
                }
            }

        }
    }

    // Turns
    public void endTurn(boolean noTimeLeft) {
        this.toggleUserInterface(false);

        if(noTimeLeft) {
            this.stopTurnTimer();

            this.changeBannerLabels("Zug beendet!", "Nächster Zug wird vorbereitet...", Color.web("#cdb39c"));
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
        if(e.isMyTurn()) {
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
        if(this.turnTimerThread != null) {
            this.turnTimer.forceEnd();
            this.turnTimer = null;
            this.turnTimerThread = null;
        }
    }

    public void updateTurnTimer(double seconds) {
        this.view.getTurnTimerProgress().setProgress(seconds / (double) turnTime);

        if(seconds <= 0.0) {
            this.endTurn(true);
        }
    }

    // User Interface
    public void toggleUserInterface(boolean show) {
        view.getHoldingArea().setVisible(!show);
        view.getHoldingArea().toBack();
        view.getUserInterface().setVisible(show);
    }

    public void fullscreen() {
        sc.toggleFullscreen();
    }

    public void changeBgGradient(Color color) {
        Stop[] stops = new Stop[] {
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
}
