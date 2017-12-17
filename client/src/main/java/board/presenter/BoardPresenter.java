package board.presenter;


import GameEvents.GameInfoEvent;
import GameEvents.TurnEvent;
import board.model.TurnTimerThread;
import board.view.BoardViewImplFx;
import board.view.StorageViewImplFx;
import commonLobby.CLTLobby;
import commonLobby.LobbyUser;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import main.SceneController;

import java.io.IOException;
import java.util.ArrayList;


public class BoardPresenter {
    private BoardViewImplFx view;
    private SceneController sc;
    private CLTLobby lobby;
    private ArrayList<StoragePresenter> storagePresenters = new ArrayList<>();

    //Board Variables
    private ArrayList<int[]> ships;
    private int round;
    private boolean[] storages;
    private String[] order;
    private int turnTime;
    private Thread turnTimerThread;

    // Konstruktor
    public BoardPresenter(BoardViewImplFx view, SceneController sc, CLTLobby legacy) {
        this.lobby = legacy;
        this.view = view;
        this.sc = sc;
        this.turnTime = 0;
        int render = 0;
        this.turnTimerThread = null;

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

    // Board View
    public BoardViewImplFx getView() {
        return view;
    }

    public void updateBoard(GameInfoEvent e) {
        storages = e.getStorages();
        ships = e.getShips();
        round = e.getRound();
        order = e.getOrder();
        turnTime = e.getTurnTime();

        updateView();
    }

    private void updateView() {
        updateStorages();
    }

    private void updateStorages() {
        int i = 0;
        int[] playerStorages = new int[storages.length / 5];
        for (boolean stone : storages) {
            if (stone) {
                playerStorages[i / 5]++;
            }
            if ((i + 1) % 5 == 0) {
                storagePresenters.get(i / 5).setStoneCount(playerStorages[i / 5]);
            }
            i++;
        }
    }

    // Turns
    public void endTurn() {
        System.out.println("endTurn1");
        this.toggleUserInterface(false);
        this.stopTurnTimer();
    }

    public void newTurn(TurnEvent e) {
        System.out.println("newTurn1");
        System.out.println("turnTime: " +this.turnTime);
        // Buttons anzeigen, wenn Spieler aktuell an der Reihe ist
        this.view.getGameLog().getChildren().clear();
        this.toggleUserInterface(e.isMyTurn());

        String msg;
        if(e.isMyTurn()) {
            msg = "Du bist am Zug";
            this.startTurnTimer();
        } else {
            msg = "Spieler " + e.getUsername() + " ist am Zug";
        }

        this.addLogMessage(msg);
        System.out.println("newTurn2");
    }

    private void startTurnTimer() {
        System.out.println("startTimer1");
        this.stopTurnTimer();

        this.turnTimerThread = new Thread(new TurnTimerThread(this, this.turnTime));
        this.turnTimerThread.start();

        System.out.println("startTimer2");
    }

    public void stopTurnTimer() {
        System.out.println("stopTimer1");
        this.view.getTurnTimer().getChildren().clear();

        if(this.turnTimerThread != null) {
            System.out.println("stopTimer2");
            this.turnTimerThread.interrupt();
            this.turnTimerThread = null;
        }

        System.out.println("stopTimer3");
    }

    public void updateTurnTimer(int seconds) {
        Text text = new Text("Verbleibende Zeit: " + seconds + "\n");

        this.view.getTurnTimer().getChildren().clear();
        this.view.getTurnTimer().getChildren().add(text);

        if(seconds <= 0) {
            this.endTurn();
        }
    }

    // User Interface
    public void toggleUserInterface(boolean show) {
        view.getUserInterface().setVisible(show);
    }

    public void addLogMessage(String message) {
        Text text = new Text(message + "\n");

        this.view.getGameLog().getChildren().addAll(text);
    }

    public void fullscreen() {
        sc.toggleFullscreen();
    }
}
