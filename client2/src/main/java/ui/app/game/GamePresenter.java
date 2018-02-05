package ui.app.game;

import com.google.common.eventbus.EventBus;
import connection.Connection;
import data.lobby.CommonLobby;
import data.user.User;
import mvp.presenter.Presenter;

public class GamePresenter extends Presenter<IGameView> {

  private final Connection connection;
  private final User user;
  private CommonLobby lobby;

  public GamePresenter(IGameView view, EventBus eventBus, Connection connection, CommonLobby lobby,
      User user) {
    super(view, eventBus);
    this.connection = connection;
    this.user = user;
    this.lobby = lobby;
  }

  public Connection getClientSocket() {
    return this.connection;
  }

  public void logout() {
    //getClientSocket().send(new LogoutRequest());
    //TODO Überlegen wie das umgesetzt werden soll
    //this.chatPresenter.getChatView().getChatText().getChildren().clear();
  }
}
