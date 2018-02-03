package ui.app.lobby;

import com.google.common.eventbus.EventBus;
import connection.Connection;
import data.lobby.CommonLobby;
import data.user.User;
import misc.soundtrack.Soundtrack;
import mvp.presenter.Presenter;
import requests.IRequest;
import requests.StartGameRequest;

public class LobbyPresenter extends Presenter<ILobbyView> {

  private final Connection connection;
  private final User user;
  private CommonLobby lobby;

  public LobbyPresenter(ILobbyView view, EventBus eventBus, Connection connection, User user, CommonLobby lobby) {
    super(view, eventBus);
    this.connection = connection;
    this.user = user;
    this.lobby = lobby;
  }

  public Connection getClientSocket() {
    return this.connection;
  }

  public CommonLobby getLobby() {
    return lobby;
  }

}
