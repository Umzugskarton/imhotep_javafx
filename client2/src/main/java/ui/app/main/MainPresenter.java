package ui.app.main;

import com.google.common.eventbus.EventBus;
import connection.Connection;
import data.user.User;
import mvp.presenter.Presenter;
import requests.main.LogoutRequest;

public class MainPresenter extends Presenter<IMainView> {

  private final Connection connection;
  private final User user;

  public MainPresenter(IMainView view, EventBus eventBus, Connection connection, User user) {
    super(view, eventBus);
    this.connection = connection;
    this.user = user;
  }

  public Connection getConnection() {
    return this.connection;
  }

  public void logout() {
    this.connection.send(new LogoutRequest());
    //TODO Überlegen wie das umgesetzt werden soll
    //this.chatPresenter.getChatView().getChatText().getChildren().clear();
  }
}
