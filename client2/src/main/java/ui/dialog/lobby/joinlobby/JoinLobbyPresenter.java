package ui.dialog.lobby.joinlobby;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import connection.Connection;
import events.app.lobby.create.CreateLobbyFailedEvent;
import events.app.lobby.create.CreateLobbySuccessfulEvent;
import events.app.lobby.join.JoinLobbyEvent;
import events.app.lobby.join.JoinLobbyFailedEvent;
import events.app.lobby.join.JoinLobbySuccessfulEvent;
import mvp.presenter.Presenter;
import requests.lobby.JoinRequest;
import ui.app.main.lobbylist.LobbyTableData;

public class JoinLobbyPresenter extends Presenter<IJoinLobbyView> {

  private final Connection connection;

  private LobbyTableData lobbydata;

  public JoinLobbyPresenter(IJoinLobbyView view, LobbyTableData lobbydata, EventBus eventBus,
      Connection connection) {
    super(view, eventBus);
    this.connection = connection;
    this.lobbydata = lobbydata;
    bind();
  }

  private void bind(){
    getEventBus().register(this);
  }

  public Connection getConnection() {
    return this.connection;
  }

  public LobbyTableData getLobbyData() {
    return lobbydata;
  }

  public void joinLobby(String pass) {
    if (pass.length() >= 16) {
      this.view.updateStatusLabel("Passwort zu lang. (Maximal 16 Zeichen)");
    } else {
      JoinRequest r = new JoinRequest(this.lobbydata.getLobbyId(), pass);
      this.connection.send(r);
    }
  }

  @Subscribe
  public void onJoinLobbySuccessfulEvent(JoinLobbyEvent e){
    if(e.getSuccess()){
      this.getView().closeDialog();
    } else{
      this.getView().updateStatusLabel(e.getMsg());
    }
  }
}
