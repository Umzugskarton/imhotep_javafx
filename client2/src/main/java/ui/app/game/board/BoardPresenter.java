package ui.app.game.board;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import connection.Connection;
import data.lobby.CommonLobby;
import data.user.User;
import events.app.game.GameInfoEvent;
import javafx.application.Platform;
import mvp.presenter.Presenter;

public class BoardPresenter extends Presenter<IBoardView> {
  private final Connection connection;
  private final User user;
  private CommonLobby lobby;

  public BoardPresenter(IBoardView view, EventBus eventBus, Connection connection, User user, CommonLobby lobby) {
    super(view, eventBus);
    this.connection = connection;
    this.user = user;
    this.lobby= lobby;
    bind();
  }

  public void bind(){
    eventBus.register(this);
  }

  public Connection getClientSocket() {
    return this.connection;
  }

  //TODO alle updates zum seten der Sites
  @Subscribe
  private void update(GameInfoEvent event){
    Platform.runLater(
            () -> {
              view.setShips(event.getShips());
            });
  }

  @Subscribe
  private void setProgressbar(Double time){
    this.view.getTurnTimerProgress().setProgress(time);
  }

}
