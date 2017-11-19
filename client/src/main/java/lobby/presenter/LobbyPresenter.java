package lobby.presenter;

import CLTrequests.changeColorRequest;
import CLTrequests.setReadyRequest;
import commonLobby.CLTLobby;
import lobby.view.LobbyView;
import main.SceneController;

import java.util.ArrayList;
import java.util.Arrays;

public class LobbyPresenter {

  private SceneController sc;
  private LobbyView lobbyView;
  private CLTLobby CLTLobby;
  private String username;

  public LobbyPresenter(LobbyView lobbyView, SceneController sc) {
    this.sc = sc;
    this.lobbyView = lobbyView;
    lobbyView.setLobbyPresenter(this);
    setUsername(this.sc.getMainmenuPresenter().getUsername());
  }

  public CLTLobby getCLTLobby() {
    return this.CLTLobby;
  }

  public void updateLobby(CLTLobby lobby){
    CLTLobby = lobby;
    lobbyView.updateTable();
  }
  public void setCLTLobby(CLTLobby CLTLobby) {
    this.CLTLobby = CLTLobby;
    lobbyView.initLobbyInfo();
  }

  public void sendChangeColorRequest() {
    //this.lobbyView.updateColorRectangle();
    changeColorRequest changeColorRequest = new changeColorRequest();
    this.getSceneController().getClientSocket().send(changeColorRequest);

  }

  public void sendSetReadyRequest() {
    setReadyRequest setReadyRequest = new setReadyRequest();
    this.getSceneController().getClientSocket().send(setReadyRequest);
  }


  public boolean checkAllReady() {
    return Arrays.asList(CLTLobby.getReady()).contains(false);
  }

  public void startGame() {
    System.out.print("Game Start!");
  }

  public boolean checkHost() {
     return this.CLTLobby.getHost().equals(username);
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
