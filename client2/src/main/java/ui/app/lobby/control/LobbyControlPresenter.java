package ui.app.lobby.control;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import connection.Connection;
import events.start.login.LoginFailedEvent;
import mvp.presenter.Presenter;
import requests.LoginRequest;

public class LobbyControlPresenter extends Presenter<ILobbyControlView> {

  private final Connection connection;

  public LobbyControlPresenter(ILobbyControlView view, EventBus eventBus, Connection connection) {
    super(view, eventBus);
    this.connection = connection;
    getEventBus().register(this);
  }
}