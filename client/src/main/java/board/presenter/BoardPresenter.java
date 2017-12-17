package board.presenter;


import GameEvents.GameInfoEvent;
import board.view.BoardViewImplFx;
import board.view.StorageViewImplFx;
import commonLobby.CLTLobby;
import commonLobby.LobbyUser;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
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


  public BoardPresenter(BoardViewImplFx view, SceneController sc, CLTLobby legacy) {
    this.lobby = legacy;
    this.view = view;
    this.sc = sc;
    int render = 0;
    int test = 0;
    // zum testen
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
  }

  private void updateView(){
    updateStorages();

  }

  private void updateStorages(){
    int i = 0;
    int[] playerStorages  = new int[storages.length/5];
    for (boolean stone: storages){
      if (stone){
        playerStorages[i/5]++;
      }
      if ((i+1) % 5 == 0 ){
        storagePresenters.get(i/5).setStoneCount(playerStorages[i/5]);
      }
      i++;
    }
  }


  public void fullscreen() {
    sc.toggleFullscreen();
  }


}
