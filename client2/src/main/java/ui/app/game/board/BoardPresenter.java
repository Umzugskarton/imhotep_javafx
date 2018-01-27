package ui.app.game.board;

import com.google.common.eventbus.EventBus;
import connection.Connection;
import data.user.User;
import mvp.presenter.Presenter;

public class BoardPresenter extends Presenter<IBoardView> {
  private final Connection connection;
  private final User user;

  public BoardPresenter(IBoardView view, EventBus eventBus, Connection connection, User user) {
    super(view, eventBus);
    this.connection = connection;
    this.user = user;
  }

  public Connection getClientSocket() {
    return this.connection;
  }
}
