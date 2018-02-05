package ui.start;

import com.google.common.eventbus.EventBus;
import connection.Connection;
import mvp.presenter.Presenter;

public class StartPresenter extends Presenter<IStartView> {

  private Connection connection;

  public StartPresenter(IStartView view, EventBus eventBus, Connection connection) {
    super(view, eventBus);
    this.connection = connection;
  }

  public Connection getConnection() {
    return this.connection;
  }
}
