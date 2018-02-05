package commands.lobby;

import commands.Command;
import data.lobby.CommonLobby;
import data.user.User;
import events.app.lobby.leave.LeaveLobbyEvent;
import events.app.lobby.LobbyInfoEvent;
import lobby.Lobby;
import requests.IRequest;
import requests.lobby.LeaveLobbyRequest;
import socket.ClientListener;
import socket.Server;

public class LeaveLobbyCommand implements Command {

  private ClientListener clientListener;
  private LeaveLobbyRequest request;
  private Server server;

  public LeaveLobbyCommand(ClientListener clientListener) {
    this.clientListener = clientListener;
    this.server = clientListener.getServer();
  }

  @Override
  public void put(IRequest r) {
    this.request = (LeaveLobbyRequest) r;
  }

  @Override
  public void exec() {
    User user = this.clientListener.getUser();
    Lobby lobby = this.clientListener.getServer().getLobbybyID(request.getLobbyId());
    //clientListener.addLobby(lobby);

    LeaveLobbyEvent response = lobby.leave(user);
    server.sendTo(response, clientListener.getUser().getUsername());
    this.clientListener.addLobby(null);
    if (lobby.getUsers()[0] != null) {
      this.clientListener.getServer()
          .sendToLoggedIn(this.server.getLobbies(clientListener.getUser()));
      CommonLobby cltLobby = new CommonLobby(lobby.getLobbyID(), lobby.getName(),
          lobby.getLobbyUserArrayList(), lobby.hasPW(), lobby.getSize(), lobby.isHost(user),
          lobby.getHostName(), lobby.getReady(), lobby.getColors());
      LobbyInfoEvent lobbyInfo = new LobbyInfoEvent(cltLobby);
      server.sendToLobby(lobbyInfo, lobby);
    } else {
      LobbyInfoEvent lobbyInfoEmpty = new LobbyInfoEvent();
      server.sendToLobby(lobbyInfoEmpty, lobby);
    }
  }
}
