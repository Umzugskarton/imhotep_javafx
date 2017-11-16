package lobby.presenter;

import CLTrequests.changeColorRequest;
import commonLobby.CLTLobby;
import lobby.view.LobbyView;
import main.SceneController;

import java.util.ArrayList;

public class LobbyPresenter {

  private SceneController sc;
  private LobbyView lobbyView;
  private CLTLobby CLTLobby;
  private String username;

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
    setUsername(this.sc.getMainmenuPresenter().getUsername());
    lobbyView.initLobbyInfo();
  }

  public void sendChangeColorRequest() {
    //this.lobbyView.updateColorRectangle();
    changeColorRequest changeColorRequest = new changeColorRequest();
    this.getSceneController().getClientSocket().send(changeColorRequest);

  }

  public void updatePlayerColor(ArrayList<String> colors) {
    CLTLobby.setColors(colors);
    lobbyView.initLobbyInfo();
  }

  public LobbyView getLobbyView() {
    return this.lobbyView;
  }

  public SceneController getSceneController() {
    return this.sc;
  }

  public void setUsername(String username){ this.username = username; }

  public String getUsername() { return this.username; }
}
