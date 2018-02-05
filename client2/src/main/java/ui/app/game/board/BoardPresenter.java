package ui.app.game.board;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import connection.Connection;
import data.lobby.CommonLobby;
import data.user.User;
import events.app.game.GameInfoEvent;
import events.app.game.ShipDockedEvent;
import events.app.game.UpdatePointsEvent;
import mvp.presenter.Presenter;

public class BoardPresenter extends Presenter<IBoardView> {

  private final Connection connection;
  private final User user;
  private CommonLobby lobby;

  public BoardPresenter(IBoardView view, EventBus eventBus, Connection connection, User user,
      CommonLobby lobby) {
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

  @Subscribe
  private void update(GameInfoEvent event) {
    view.setShips(event.getShips());
  }

  @Subscribe
  private void setProgressbar(Double time) {
    this.view.getTurnTimerProgress().setProgress(time);
  }

  @Subscribe
  private void onShipDockedEvent(ShipDockedEvent event) {
    view.getPierByType(event.getSite()).getChildren()
        .add(view.removeShipPaneById(event.getShipID()));
  }

  @Subscribe
  public void onUpdatePoints(UpdatePointsEvent event) {
    updatePointsView(event.getPoints());
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

      view.getStorageViews().get(playerWithHighestPoints).highlightPointsLabel(false);
      view.getStorageViews().get(i).setPoints(points);
    }

    if (highestPoints != 0) {
      view.getStorageViews().get(playerWithHighestPoints).highlightPointsLabel(true);
    }
  }

}
