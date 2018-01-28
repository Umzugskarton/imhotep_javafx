package ui.app.game.board;

import GameEvents.GameInfoEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import connection.Connection;
import data.lobby.Lobby;
import data.user.User;
import javafx.application.Platform;
import mvp.presenter.Presenter;

public class BoardPresenter extends Presenter<IBoardView> {
  private final Connection connection;
  private final User user;
  private Lobby lobby;

  public BoardPresenter(IBoardView view, EventBus eventBus, Connection connection, User user, Lobby lobby) {
    super(view, eventBus);
    this.connection = connection;
    this.user = user;
    this.lobby= lobby;
  }

  public Connection getClientSocket() {
    return this.connection;
  }

  @Subscribe
  private void setGameInfoEvent(GameInfoEvent event){
    Platform.runLater(
            () -> {

            });
  }
}
