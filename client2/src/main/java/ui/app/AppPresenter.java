package ui.app;

import com.google.common.eventbus.EventBus;
import connection.Connection;
import mvp.presenter.Presenter;

public class AppPresenter extends Presenter<IAppView> {

  private Connection connection;

  public AppPresenter(IAppView view, EventBus eventBus, Connection connection) {
    super(view, eventBus);
    this.connection = connection;
  }

  public Connection getConnection() {
    return this.connection;
  }
}
