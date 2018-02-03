package ui.app.lobby.control;

import com.google.common.eventbus.EventBus;
import connection.Connection;
import data.lobby.CommonLobby;
import mvp.presenter.Presenter;
import requests.ChangeColorRequest;
import requests.IRequest;
import requests.SetReadyRequest;
import requests.StartGameRequest;

public class LobbyControlPresenter extends Presenter<ILobbyControlView> {

  private CommonLobby lobby;

  private final Connection connection;

  public LobbyControlPresenter(ILobbyControlView view, EventBus eventBus, Connection connection, CommonLobby lobby) {
    super(view, eventBus);
    this.connection = connection;
    this.lobby = lobby;
    getEventBus().register(this);
  }

  public void sendChangeColorRequest() {
    //this.lobbyView.updateColorRectangle();
    ChangeColorRequest changeColorRequest = new ChangeColorRequest(lobby.getLobbyId());
    this.connection.send(changeColorRequest);
  }

  public void sendSetReadyRequest() {
    SetReadyRequest setReadyRequest = new SetReadyRequest(lobby.getLobbyId());
    this.connection.send(setReadyRequest);
  }

  public void startGame() {
    System.out.println("Ich starte das Spiel ist im Presenter " + lobby.getUsers().size() + " " + lobby.getSize());
    if (lobby.getUsers().size() == lobby.getSize()) {
      IRequest request = new StartGameRequest(lobby.getLobbyId());
      connection.send(request);
//      Soundtrack.imhotepTheme.loop();
    }
    else {
      //ToDo: Message ausgeben das noch nicht genug Spieler gejoined sind
      System.out.println("Nicht genug Spieler sind in der Lobby");
    }
  }
}