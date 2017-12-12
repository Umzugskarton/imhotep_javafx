package board.presenter;


import GameEvents.gameInfoEvent;
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

  public void updateBoard(gameInfoEvent e) {

  }

  public void fullscreen() {
    sc.toggleFullscreen();
  }


}
