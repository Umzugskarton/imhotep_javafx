package ui.dialog.lobby.createlobby;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import connection.Connection;
import events.app.lobby.create.CreateLobbyFailedEvent;
import events.app.lobby.create.CreateLobbySuccessfulEvent;
import mvp.presenter.Presenter;
import requests.lobby.CreateRequest;

public class CreateLobbyPresenter extends Presenter<ICreateLobbyView> {

  private final Connection connection;

  public CreateLobbyPresenter(ICreateLobbyView view, EventBus eventBus, Connection connection) {
    super(view, eventBus);
    this.connection = connection;
  }

  public Connection getConnection() {
    return this.connection;
  }

  public void createLobby(String name, int size, String pass) {
    if (name.isEmpty()) {
      this.view.updateStatusLabel("Bitte geben Sie einen Namen für die Lobby ein!");
    } else {
      CreateRequest j = new CreateRequest(name, size, pass);
      this.connection.send(j);
    }
  }

  @Subscribe
  public void onCreateLobbySuccessfulEvent(CreateLobbySuccessfulEvent e){
    this.getView().closeDialog();
  }

  @Subscribe
  public void onCreateLobbyFailedEvent(CreateLobbyFailedEvent e){
    this.getView().updateStatusLabel(e.getReason().toString());
  }
}
