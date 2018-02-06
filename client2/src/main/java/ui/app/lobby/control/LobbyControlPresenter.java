package ui.app.lobby.control;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import connection.Connection;
import data.lobby.CommonLobby;
import data.user.User;
import events.app.lobby.LobbyInfoEvent;
import mvp.presenter.Presenter;
import requests.IRequest;
import requests.lobby.ChangeColorRequest;
import requests.lobby.SetReadyRequest;
import requests.lobby.StartGameRequest;

public class LobbyControlPresenter extends Presenter<ILobbyControlView> {

  private CommonLobby lobby;

  private final Connection connection;
  private final User user;

  public LobbyControlPresenter(ILobbyControlView view, EventBus eventBus, Connection connection,
      CommonLobby lobby, User user) {
    super(view, eventBus);
    this.connection = connection;
    this.lobby = lobby;
    this.user = user;
    this.eventBus.register(this);
  }

  public CommonLobby getLobby() {
    return lobby;
  }

  public boolean isLobbyHost (){
    if(lobby.getHost().equals(this.user.getUsername())){
      return true;
    } else {
      return false;
    }
  }

  public String getUserSize(){
    return this.lobby.getBelegung();
  }

  public void sendChangeColorRequest() {
    ChangeColorRequest changeColorRequest = new ChangeColorRequest(lobby.getLobbyId());
    this.connection.send(changeColorRequest);
  }

  public void sendSetReadyRequest() {
    SetReadyRequest setReadyRequest = new SetReadyRequest(lobby.getLobbyId());
    this.connection.send(setReadyRequest);
  }

  public void startGame() {
    if (lobby.getUsers().size() == lobby.getSize()) {
      IRequest request = new StartGameRequest(lobby.getLobbyId());
      connection.send(request);
//      Soundtrack.imhotepTheme.loop();
    } else {
      this.getView().updateStatusLabel("Nicht genug Spieler sind in der Lobby");
    }
  }

  @Subscribe
  public void onLobbyInfoEvent(LobbyInfoEvent e){
    if(this.lobby.getLobbyId() == e.getLobby().getLobbyId()){
      if(e.getLobby().getHost().equals(this.user.getUsername())){
        this.getView().showStartGameButton(true);
      } else {
        this.getView().showStartGameButton(false);
      }
      getView().updateUserSizeLabel(e.getLobby().getBelegung());
    }
  }
}