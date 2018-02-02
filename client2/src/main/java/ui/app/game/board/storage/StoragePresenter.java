package ui.app.game.board.storage;

import events.app.game.GameInfoEvent;
import requests.gamemoves.FillUpStorageMove;
import requests.gamemoves.LoadUpShipMove;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import connection.Connection;
import data.lobby.LobbyUser;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import mvp.presenter.Presenter;
import java.util.ArrayList;

public class StoragePresenter extends Presenter<IStorageView> {
  private LobbyUser user;
  private int stoneCount;
  private Connection connection;
  private boolean myStorage;
  private final int lobbyId;


  public StoragePresenter(IStorageView view, EventBus eventBus, Connection connection, LobbyUser user, boolean myStorage, int lobbyId) {
    super(view , eventBus);
    this.myStorage = myStorage;
    this.user = user;
    this.connection = connection;
    this.lobbyId = lobbyId;
    bind();
  }

  @Subscribe
  public void update(GameInfoEvent e){
    Platform.runLater(
            () -> {
                setStoneCount(e.getStorages().get(lobbyId));
            });
  }

  public void bind(){
    eventBus.register(this);
  }

  public void setStoneCount(int stones){
    stoneCount = stones;
    ArrayList<Group> stoneGroup = view.getStones();
    for (int i = 0; i < stoneGroup.size() ; i++){
      stoneGroup.get(i).setVisible(false);
    }
    for (int i = 0; i < stones ; i++){
      stoneGroup.get(i).setVisible(true);
    }
  }

  public void highlightPointsLabel(boolean highlight) {
    if(highlight) {
      this.view.getPointsLabel().setUnderline(true);
      this.view.getPointsLabel().setFont(Font.font("Calibri", FontWeight.BOLD, 14));
    } else {
      this.view.getPointsLabel().setUnderline(false);
      this.view.getPointsLabel().setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
    }
  }

  public LobbyUser getUser() {
    return user;
  }

  public void setPoints(int points) {
    this.view.getPointsLabel().setText(points + "");
  }

  public int getStoneCount() {
    return stoneCount;
  }

  //Todo Connection

  @Subscribe
  public void sendFillUpStorageMove(FillUpStorageMove move) {
    if (myStorage && stoneCount < 5) {
      System.out.println("Will send "+ move.getType() + " from " + user.getUsername() +"`s Storage." );
    }
  }

  @Subscribe
  public void sendLoadUpShipMove(LoadUpShipMove move) {
    if (myStorage && stoneCount > 0) {
      System.out.println("Will send "+ move.getType() + " from " + user.getUsername() +"`s Storage." );
    }
  }

}
