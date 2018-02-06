package commands.lobby;

import commands.Command;
import data.lobby.CommonLobby;
import data.user.User;
import events.EventReason;
import events.app.lobby.join.JoinLobbyEvent;
import events.app.lobby.LobbyInfoEvent;
import events.app.lobby.join.JoinLobbyFailedEvent;
import events.app.lobby.join.JoinLobbySuccessfulEvent;
import lobby.Lobby;
import requests.IRequest;
import requests.lobby.JoinRequest;
import socket.ClientListener;
import socket.Server;

public class JoinCommand implements Command {

  private ClientListener clientListener;
  private JoinRequest request;
  private Server server;

  public JoinCommand(ClientListener clientListener) {
    this.clientListener = clientListener;
    this.server = clientListener.getServer();
  }

  @Override
  public void put(IRequest r) {
    this.request = (JoinRequest) r;
  }

  @Override
  public void exec() {
    User user = this.clientListener.getUser();
    Lobby lobby = this.clientListener.getServer().getLobbybyID(request.getLobbyId());
    if (!clientListener.getLobbies().contains(lobby)) {
      JoinLobbyEvent response;
      response = lobby.join(user, request.getPassword());
      if (response.getSuccess()) {
        clientListener.addLobby(lobby);
        CommonLobby cltLobby = new CommonLobby(lobby.getLobbyID(), lobby.getName(),
            lobby.getLobbyUserArrayList(), lobby.hasPW(), lobby.getSize(), lobby.isHost(user),
            lobby.getHostName(), lobby.getReady(), lobby.getColors());
        LobbyInfoEvent lobbyInfo = new LobbyInfoEvent(cltLobby);
        server.sendToLobby(lobbyInfo, lobby);

        clientListener.send(new JoinLobbySuccessfulEvent(cltLobby));
        server.sendToLobby(lobbyInfo, lobby);
        server.sendToLoggedIn(server.getLobbies(user));
      }
    }else {
      JoinLobbyFailedEvent event = new JoinLobbyFailedEvent();
      event.setReason(EventReason.INVALID_REQUEST);
      clientListener.send(event);
    }
  }
}
