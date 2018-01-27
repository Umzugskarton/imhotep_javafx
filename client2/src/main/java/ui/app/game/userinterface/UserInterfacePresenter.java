package ui.app.game.userinterface;


import com.google.common.eventbus.EventBus;
import connection.Connection;
import data.user.User;
import mvp.presenter.Presenter;

public class UserInterfacePresenter extends Presenter<IUserInterfaceView> {
  private final Connection connection;
  private final User user;

  public UserInterfacePresenter(IUserInterfaceView view, EventBus eventBus, Connection connection, User user) {
    super(view, eventBus);
    this.connection = connection;
    this.user = user;
  }

  public Connection getClientSocket() {
    return this.connection;
  }
}
