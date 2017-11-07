package lobby.presenter;


import commonLobby.CLTLobby;
import lobby.view.LobbyView;
import main.SceneController;

public class LobbyPresenter {

  private SceneController sc;
  private LobbyView lobbyView;
  private CLTLobby CLTLobby;

  public LobbyPresenter(LobbyView lobbyView, SceneController sc) {
    this.sc = sc;
    this.lobbyView = lobbyView;
    lobbyView.setLobbyPresenter(this);

  }

  public CLTLobby getCLTLobby() {
    return this.CLTLobby;
  }

  public void setCLTLobby(CLTLobby CLTLobby) {
    this.CLTLobby = CLTLobby;
    lobbyView.initLobbyInfo();
  }

  public LobbyView getLobbyView() {
    return this.lobbyView;
  }

  public SceneController getSceneController() {
    return this.sc;
  }
}
