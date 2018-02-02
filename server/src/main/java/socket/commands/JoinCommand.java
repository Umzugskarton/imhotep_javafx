package socket.commands;

import requests.IRequest;
import requests.joinRequest;
import data.lobby.CommonLobby;
import lobby.Lobby;
import socket.ClientListener;
import socket.Server;
import events.app.lobby.JoinLobbyEvent;
import events.app.lobby.LobbyInfoEvent;
import data.user.User;

public class JoinCommand implements Command {

  private ClientListener clientListener;
  private joinRequest request;
  private Server server;

  JoinCommand(ClientListener clientListener) {
    this.clientListener = clientListener;
    this.server = clientListener.getServer();
  }

  @Override
  public void put(IRequest r) {
    this.request = (joinRequest) r;
  }

  @Override
  public void exec() {
    User user = this.clientListener.getUser();
    Lobby lobby = this.clientListener.getServer().getLobbybyID(request.getLobbyId());
    JoinLobbyEvent response;
    response = lobby.join(user, request.getPassword());
    if (response.getSuccess()) {
      clientListener.addLobby(lobby);
      clientListener.getServer()
          .sendToLoggedIn(this.server.getLobbies(clientListener.getUser()));
      CommonLobby cltLobby = new CommonLobby(lobby.getLobbyID(), lobby.getName(),
          lobby.getLobbyUserArrayList(), lobby.hasPW(), lobby.getSize(), lobby.isHost(user),
          lobby.getHostName(), lobby.getReady(), lobby.getColors());
      LobbyInfoEvent lobbyInfo = new LobbyInfoEvent(cltLobby);
      server.sendToLobby(lobbyInfo, lobby);
    }
  }
}
