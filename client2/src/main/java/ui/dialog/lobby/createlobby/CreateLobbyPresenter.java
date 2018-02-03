package ui.dialog.lobby.createlobby;

import com.google.common.eventbus.EventBus;
import connection.Connection;
import mvp.presenter.Presenter;
import requests.CreateRequest;

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
    if(name.equals("")) {
      this.view.updateStatusLabel("Bitte geben Sie einen Namen für die Lobby ein!");
    } else if(pass.length() >= 16) {
      this.view.updateStatusLabel("Passwort zu lang. (Maximal 16 Zeichen)");
    } else {
      CreateRequest j = new CreateRequest(name, size, pass);
      this.connection.send(j);
    }
  }
}