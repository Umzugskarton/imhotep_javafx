package ui.app.game.board.storage;

import events.app.game.FillUpStorageEvent;
import events.app.game.GameInfoEvent;
import events.app.game.ShipLoadedEvent;
import requests.gamemoves.FillUpStorageMove;
import requests.gamemoves.LoadUpShipMove;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import connection.Connection;
import data.lobby.LobbyUser;
import javafx.application.Platform;
import javafx.scene.Group;
import mvp.presenter.Presenter;
import java.util.ArrayList;

public class StoragePresenter extends Presenter<IStorageView> {
  private LobbyUser user;
  private int stoneCount;
  private Connection connection;
  private boolean myStorage;
  private final int lobbyId;


  public StoragePresenter(IStorageView view, EventBus eventBus, Connection connection, LobbyUser user, boolean myStorage, int lobbyId) {
    super(view, eventBus);
    this.myStorage = myStorage;
    this.user = user;
    this.connection = connection;
    this.lobbyId = lobbyId;
    bind();
  }

  @Subscribe
  public void onGameInfoEvent(GameInfoEvent e) {
    setStoneCount(e.getStorages().get(lobbyId));
  }

  public void bind() {
    eventBus.register(this);
  }


  @Subscribe
  private void onShiploadedEvent(ShipLoadedEvent e) {
    if (lobbyId == e.getPlayerId()) {
      setStoneCount(e.getStorage());
    }
  }

  @Subscribe
  private void onFillUpStorageEvent(FillUpStorageEvent e) {
    if (lobbyId == e.getPlayerId()) {
      setStoneCount(e.getStorage());
    }
  }


  public void setStoneCount(int stones) {
    Platform.runLater(
            () -> {
              stoneCount = stones;
              ArrayList<Group> stoneGroup = view.getStones();
              for (int i = 0; i < stoneGroup.size(); i++) {
                stoneGroup.get(i).setVisible(false);
              }
              for (int i = 0; i < stones; i++) {
                stoneGroup.get(i).setVisible(true);
              }
            });
  }


  public LobbyUser getUser() {
    return user;
  }


  public int getStoneCount() {
    return stoneCount;
  }

  //Todo Connection

  @Subscribe
  public void sendFillUpStorageMove(FillUpStorageMove move) {
    if (myStorage && stoneCount < 5) {
      System.out.println("Will send " + move.getType().toString() + " from " + user.getUsername() + "`s Storage.");
    }
  }

  @Subscribe
  public void sendLoadUpShipMove(LoadUpShipMove move) {
    if (myStorage && stoneCount > 0) {
      System.out.println("Will send " + move.getType().toString() + " from " + user.getUsername() + "`s Storage.");
    }
  }

}
