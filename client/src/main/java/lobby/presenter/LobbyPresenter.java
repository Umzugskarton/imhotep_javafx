package lobby.presenter;

import requests.*;
import data.lobby.CommonLobby;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import lobby.view.LobbyView;
import main.SceneController;

import java.util.Arrays;

import requests.chat.ChatRequest;
import requests.lobby.ChangeColorRequest;
import requests.lobby.LeaveLobbyRequest;
import requests.lobby.SetReadyRequest;
import requests.lobby.StartGameRequest;
import sound.Soundtrack;

import static general.TextBundle.getString;

public class LobbyPresenter {

  private SceneController sc;
  private LobbyView lobbyView;
  private CommonLobby CLTLobby;
  private String username;

  public LobbyPresenter(LobbyView lobbyView, SceneController sc) {
    this.sc = sc;
    this.lobbyView = lobbyView;
    lobbyView.setLobbyPresenter(this);
    setUsername(this.sc.getMainmenuPresenter().getUsername());
  }

  public CommonLobby getCLTLobby() {
    return this.CLTLobby;
  }

  public void updateLobby(CommonLobby lobby) {
    CLTLobby = lobby;
    lobbyView.updateTable();
  }

  public void setCLTLobby(CommonLobby CLTLobby) {
    this.CLTLobby = CLTLobby;
    lobbyView.initLobbyInfo();
    addInfoMessage("Welcome to Lobby " + CLTLobby.getName());
  }

  public void sendChangeColorRequest() {
    //this.lobbyView.updateColorRectangle();
    ChangeColorRequest changeColorRequest = new ChangeColorRequest(getCLTLobby().getLobbyId());
    this.getSceneController().getClientSocket().send(changeColorRequest);
  }

  public void sendSetReadyRequest() {
    SetReadyRequest setReadyRequest = new SetReadyRequest(getCLTLobby().getLobbyId());
    this.getSceneController().getClientSocket().send(setReadyRequest);
  }

  public void sendChatMsg(String text) {
    if (!text.isEmpty()) {
      ChatRequest request = new ChatRequest(text);
      request.setLobbyId(CLTLobby.getLobbyId());
      sc.getClientSocket().send(request);

    } else {
      addInfoMessage(getString("enterMessageToChat"));
    }
  }

  public void addChatMessage(String user, String msg) {
    Text userText = new Text(user + ": ");
    userText.setStyle("-fx-font-weight: bold");
    Text messageText = new Text(msg + "\n");

    lobbyView.getChatText().getChildren().addAll(userText, messageText);
  }

  public void addInfoMessage(String msg) {
    addInfoMessage(msg, Color.GRAY);
  }

  public void addInfoMessage(String msg, Color color) {
    Text text = new Text(msg.toUpperCase() + "\n");
    text.setFill(color);
    text.setFont(new Font(null, 10));

    lobbyView.getChatText().getChildren().add(text);
  }

  public boolean checkAllReady() {
    return !Arrays.asList(CLTLobby.getReady()).contains(false);
  }

  public void startGame() {
    if (CLTLobby.getUsers().size() == CLTLobby.getSize()) {
      IRequest request = new StartGameRequest(this.getCLTLobby().getLobbyId());
      sc.getClientSocket().send(request);
      Soundtrack.imhotepTheme.loop();
    }
    else {
      //ToDo: Message ausgeben das noch nicht genug Spieler gejoined sind
    }
  }

  public void leaveLobbyRequest() {
    LeaveLobbyRequest leaveLobbyRequest = new LeaveLobbyRequest(CLTLobby.getLobbyId());
    this.getSceneController().getClientSocket().send(leaveLobbyRequest);
  }

  public void resetUserAfterLeaving() {
    //lobbyView.getChatText().getChildren().clear();

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

  public void setUsername(String username) {
    this.username = username;
  }

  public String getUsername() {
    return this.username;
  }

}