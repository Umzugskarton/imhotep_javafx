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
    private Thread turnTimerThread;


    public BoardPresenter(BoardViewImplFx view, SceneController sc, CLTLobby legacy) {
        this.lobby = legacy;
        this.view = view;
        this.sc = sc;
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

    public void toggleUserInterface(boolean show) {
        view.getUserInterface().setVisible(show);
    }

    public BoardViewImplFx getView() {
        return view;
    }

    public SceneController getSc() {
        return sc;
    }

    public void updateBoard(GameInfoEvent e) {
        storages = e.getStorages();
        ships = e.getShips();
        round = e.getRound();
        order = e.getOrder();

        updateView();

        // DEBUG
        this.addLogMessage("Runde " + e.getRound() + " gestartet");
    }

    public void newTurn(TurnEvent e) {
        // Buttons anzeigen, wenn Spieler aktuell an der Reihe ist
        this.view.getGameLog().getChildren().clear();
        this.toggleUserInterface(e.isMyturn());

        String msg;
        if(e.isMyturn()) {
            msg = "Du bist am Zug";
            startTurnTimer();
        } else {
            msg = "Spieler " + e.getUsername() + " ist am Zug";
        }

        this.addLogMessage(msg);
    }

    private void startTurnTimer() {
        this.stopTurnTimer();

        this.turnTimerThread = new Thread(new TurnTimerThread(this, 30));
        this.turnTimerThread.start();
    }

    private void stopTurnTimer() {
        if(this.turnTimerThread != null)
            this.turnTimerThread.interrupt();
    }

    private void updateView() {
        updateStorages();
    }

    public void addLogMessage(String message) {
        Text text = new Text(message + "\n");

        this.view.getGameLog().getChildren().addAll(text);
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


    public void fullscreen() {
        sc.toggleFullscreen();
    }
}
