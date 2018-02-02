package ui.dialog.createLobby;

import com.google.common.eventbus.EventBus;
import connection.Connection;
import mvp.presenter.Presenter;

public class CreateLobbyPresenter extends Presenter<ICreateLobbyView> {

  private final Connection connection;

  public CreateLobbyPresenter(ICreateLobbyView view, EventBus eventBus, Connection connection) {
    super(view, eventBus);
    this.connection = connection;
  }

  public Connection getConnection() {
    return this.connection;
  }
}
